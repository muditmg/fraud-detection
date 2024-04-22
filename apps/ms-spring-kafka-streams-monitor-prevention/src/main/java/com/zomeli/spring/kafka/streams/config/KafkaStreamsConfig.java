package com.zomeli.spring.kafka.streams.config;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

@Configuration
@EnableKafka
@EnableKafkaStreams
@Getter
public class KafkaStreamsConfig {

    @Value(value = "${kafka.bootstrap.servers}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic.input.transaction: transactionsTopic}")
    private String inTopic;

    @Value(value = "${kafka.topic.output.fraud: fraudTopic}")
    private String outTopic;

    @Value(value = "${kafka.session.inactivity-gap: 30}")
    private int sessionInactivityGap;

    @Value(value = "${kafka.session.suppressed.time-limit: 30}")
    private int suppressedTimeLimitSeconds;

    @Value(value = "${kafka.session.suppressed.max-record: 1000}")
    private int suppressedMaxRecordsBuffer;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kafkaStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, "ms-spring-kafka-streams");
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(props);
    }

}