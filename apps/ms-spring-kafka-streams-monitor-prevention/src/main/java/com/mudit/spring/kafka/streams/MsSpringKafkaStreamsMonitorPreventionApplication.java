package com.zomeli.spring.kafka.streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zomeli.spring.kafka.streams", "com.zomeli.spring.kafka.controller"})
public class MsSpringKafkaStreamsMonitorPreventionApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsSpringKafkaStreamsMonitorPreventionApplication.class, args);
	}
}