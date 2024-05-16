package com.kafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaListenerConfig {
    @KafkaListener(topics = "test", groupId = "test")
    public void listen(String message) {
        System.out.println("receive message: " + message);
    }

}
