package com.zhy.mytest.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhy
 * @Date:2020/10/29
 * @Description:
 **/
@Configuration
public class ElasticSearchClientConfig {


    /**
     * 构建RestHighLevelClient 客户端  这里可以连接多个节点  使他们构成集群
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.5.210", 9200, "http")
                )
        );
        return client;
    }
}
