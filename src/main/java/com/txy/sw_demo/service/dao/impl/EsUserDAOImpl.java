package com.txy.sw_demo.service.dao.impl;

import com.alibaba.fastjson.JSON;
import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.UserDAO;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/5 10:19
 */
@Component
public class EsUserDAOImpl implements UserDAO {
    @Value("${monitor.es.address}")
    private String esAddress;
    private RestHighLevelClient client;

    String index = "user";
    String type = "_doc";

    private RestHighLevelClient defaultClient(){
        if(client == null){
            String[] strs = esAddress.split(":");
            String ip = strs[0];
            int port = Integer.valueOf(strs[1]);

            this.client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(ip, port, "http"),
                            new HttpHost(ip, port + 1, "http")));
        }
        return client;
    }


    @Override
    public Object add(User user) {
        IndexRequest indexRequest = new IndexRequest(index, type).source("name", user.getName(), "age", user.getAge());
        try {
            defaultClient().index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Object get(User user) {
        return null;
    }

    @Override
    public Object list() {
        SearchRequest searchRequest = new SearchRequest(index);
        List<Object> result = new ArrayList<>();
        try {
            SearchResponse searchResponse = defaultClient().search(searchRequest);

            SearchHits hits = searchResponse.getHits();
            for(SearchHit hit : hits){
                String sourceAsString = hit.getSourceAsString();
                result.add(JSON.parse(sourceAsString));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object delete(User user) {
        return null;
    }
}