package com.kafka.config;

import com.kafka.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaListenerConfig {

    @Autowired
    private EsService esService;

    @KafkaListener(topics = "test", groupId = "test")
    public void listen(String message) {
        System.out.println("receive message: " + message);
        esService.save(message);
    }

}
