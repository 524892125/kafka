package com.kafka.config;

import com.google.gson.Gson;
import com.kafka.entity.EsDataEntity;
import com.kafka.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaListenerConfig {

    @Autowired
    private EsService esService;

    @KafkaListener(topics = "test", groupId = "test")
    public void listen(String message) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> formatData = gson.fromJson(message, map.getClass());
        esService.save(formatData);
    }

}
