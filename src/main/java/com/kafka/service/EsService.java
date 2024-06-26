package com.kafka.service;

import com.kafka.config.ElasticsearchClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EsService {

    @Autowired
    private ElasticsearchClient client;

    @Value("${esa.index}")
    private String indexName;

    public void save(Map<String, Object> data) {
        Map<String, Object> jsonMap = data;

        IndexRequest indexRequest = new IndexRequest(indexName)
                .source(jsonMap);

        try {
            IndexResponse indexResponse = client.getClient().index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(indexResponse.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
