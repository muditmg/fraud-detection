package com.zomeli.spring.kafka.controller;

import com.zomeli.spring.kafka.model.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MovementGeneratorController {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    @Autowired
    public MovementGeneratorController(KafkaTemplate<String, Transaction> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Transaction transaction){
        log.info(transaction);
        kafkaTemplate.send("input-monitor-trx-stream-topic", transaction);
        log.info(String.format("[ Transaction - %s ] %s", transaction.getAccount().getNumber(), transaction));
    }

}
