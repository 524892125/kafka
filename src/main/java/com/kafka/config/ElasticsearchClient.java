package com.kafka.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestClient;
import org.apache.http.HttpHost;

import java.io.IOException;

public class ElasticsearchClient {
    private static final RestHighLevelClient client;

    static {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.9.128", 9200, "http")
                )
        );
    }

    public static RestHighLevelClient getClient() {
        return client;
    }

    public static void closeClient() throws IOException, IOException {
        if (client != null) {
            client.close();
        }
    }
}

