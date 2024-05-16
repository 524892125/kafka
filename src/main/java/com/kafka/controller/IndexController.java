package com.kafka.controller;

import com.google.gson.Gson;
import com.kafka.config.ElasticsearchClient;
import com.kafka.config.KafkaListenerConfig;
import com.kafka.entity.EsDataEntity;
import com.kafka.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class IndexController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/send")
    public String send(@RequestBody EsDataEntity esDataEntity) {
        Gson gson = new Gson();
        String data = gson.toJson(esDataEntity);
        this.kafkaTemplate.send("test", data);
        return "send" + data;
    }
}
