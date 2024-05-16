package com.kafka.config;

import jakarta.annotation.PostConstruct;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestClient;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ElasticsearchClient {
    private RestHighLevelClient client;

    @Value("${esa.host}")
    private String host;

    @Value("${esa.port}")
    private int port;

    @Value("${esa.scheme}")
    private String scheme;


    @PostConstruct
    public void run() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, scheme)
                )
        );
    }

    public RestHighLevelClient getClient() {
        return client;
    }
}

