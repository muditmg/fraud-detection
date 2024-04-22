package com.zomeli.spring.kafka.streams.topologies;


import com.zomeli.spring.kafka.streams.builder.FraudTransactionBuilder;
import com.zomeli.spring.kafka.streams.config.KafkaStreamsConfig;
import com.zomeli.spring.kafka.streams.model.FraudTransaction;
import com.zomeli.spring.kafka.streams.model.MovementsAggregation;
import com.zomeli.spring.kafka.streams.model.TransactionAccount;
import com.zomeli.spring.kafka.streams.serializers.JsonDeserializer;
import com.zomeli.spring.kafka.streams.serializers.JsonSerializer;
import com.zomeli.spring.kafka.streams.uitls.TransactionUtils;
import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Logger;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.SessionWindows;
import org.apache.kafka.streams.kstream.Suppressed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FraudTransactionTopologies {

  @Autowired
  KafkaStreamsConfig config;

  @Autowired
  FraudTransactionBuilder fraudTransactionBuilder;

  private static final Logger log = Logger.getLogger(String.valueOf(FraudTransactionTopologies.class));

  private final Serde<MovementsAggregation> movementsAggregationSerde =
      Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(MovementsAggregation.class));

  private final Serde<FraudTransaction> fraudTransactionSerde =
      Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(FraudTransaction.class));

  private final Serde<TransactionAccount> transactionAccountSerde =
      Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(TransactionAccount.class));


  @Autowired
  public Topology topology(StreamsBuilder streamsBuilder){

//    final StreamsBuilder builder = new StreamsBuilder();
    Serde<String> stringSerde = Serdes.String();


    //Reads the input message from the kafka topic and the TransactionAccount deserializer Object
    KStream<String, TransactionAccount> stream = streamsBuilder
        .stream(config.getInTopic(), Consumed.with(stringSerde, transactionAccountSerde));

    KStream<String, FraudTransaction> fraudTransactionKStream = stream
        .map((key, v) -> KeyValue.pair(TransactionUtils.generatedIdWithAccountIdAndProductCodeAndSubProductCode(v), v))
        .peek((k,v) -> log.info("[Key Value Group By]: " + k))
        .groupByKey(Grouped.with(stringSerde, transactionAccountSerde))
        .windowedBy(SessionWindows.ofInactivityGapWithNoGrace(Duration.ofSeconds(config.getSessionInactivityGap())))
        .aggregate(
            MovementsAggregation::new,
            TransactionUtils::addMovement,
            TransactionUtils::aggMerge,
            Materialized.with(stringSerde, movementsAggregationSerde)
        )
//        .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded().shutDownWhenFull()))
//        .suppress(Suppressed.untilTimeLimit(Duration.ofSeconds(config.getSuppressedTimeLimitSeconds()),
//            Suppressed.BufferConfig.maxRecords(config.getSuppressedMaxRecordsBuffer())))
        .toStream()
        .filter((k,v) -> TransactionUtils.isOnlineFraud(v))
        .map((k,v) -> KeyValue.pair(String.valueOf(v.getId()), fraudTransactionBuilder.movementsAggregationToFraudCase(v)))
        .peek((k,v) -> log.info("[Fraud Detect]: " + v));

    fraudTransactionKStream
        .to(config.getOutTopic(),Produced.with(stringSerde,fraudTransactionSerde));

    return streamsBuilder.build();
  }


  public Topology splitMessage(){
//    Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
    StreamsBuilder builder = new StreamsBuilder();

    KStream<String, String> source = builder.stream(
            config.getInTopic(), Consumed.with(Serdes.String(), Serdes.String()))
        .peek((k,v) -> log.info("Message input: " + v));

    source.flatMapValues(value -> Arrays.asList(value.split("\\W+")))
        .peek((k,v) -> log.info("Message Split output:" + v))
        .to(config.getOutTopic());

    return builder.build();
  }

  public Topology filterAndUpperCase(){

    StreamsBuilder builder = new StreamsBuilder();

    KStream<String, String> kStream = builder.stream(
            config.getInTopic(), Consumed.with(Serdes.String(), Serdes.String()))
        .peek((k,v) -> log.info("Message input: " + v));


    kStream.filter((key, value) -> value.startsWith("Message_"))
        .mapValues((k, v) -> v.toUpperCase())
        .peek((k, v) -> System.out.println("Key: " + k + " Value: " + v))
        .to(config.getOutTopic(), Produced.with(Serdes.String(), Serdes.String()));

    return builder.build();
  }


}
