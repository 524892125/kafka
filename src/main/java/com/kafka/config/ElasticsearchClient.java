package com.kafka.config;

import jakarta.annotation.PostConstruct;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
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
        CredentialsProvider credentialsProvider;
        if (false) { // 如果有密码
            credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "zhihuigw-elasticsearch"));
        } else {
            credentialsProvider = null;
        }


        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, scheme)
                ).setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
        );
    }

    public RestHighLevelClient getClient() {
        return client;
    }
}

