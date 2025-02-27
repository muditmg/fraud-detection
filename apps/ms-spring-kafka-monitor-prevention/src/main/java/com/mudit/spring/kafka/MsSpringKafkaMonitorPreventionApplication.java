package com.zomeli.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zomeli.spring.kafka", "com.zomeli.spring.kafka.controller"})
public class MsSpringKafkaMonitorPreventionApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsSpringKafkaMonitorPreventionApplication.class, args);
	}
}