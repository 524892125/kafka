package com.kafka.controller;

import com.kafka.config.ElasticsearchClient;
import com.kafka.config.KafkaListenerConfig;
import com.kafka.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class IndexController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/send")
    public String send(@RequestParam Map<String, Object> param) {
        this.kafkaTemplate.send("test", param.get("input").toString());
        return "send" + param.get("input");
    }
}
