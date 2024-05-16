package com.kafka.service;

import com.kafka.config.ElasticsearchClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EsService {

    private final RestHighLevelClient client;

    public EsService() {
        this.client = ElasticsearchClient.getClient();
    }

    public void save() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "Product1");
        jsonMap.put("description", "Description of Product1");
        jsonMap.put("price", 100.0);

        IndexRequest indexRequest = new IndexRequest("test_index")
                .source(jsonMap);

        try {
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(indexResponse.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 在应用程序关闭时调用此方法来关闭客户端
    public void closeClient() {
        try {
            ElasticsearchClient.closeClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
