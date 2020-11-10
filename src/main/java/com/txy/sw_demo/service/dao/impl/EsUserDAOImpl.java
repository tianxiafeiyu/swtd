package com.txy.sw_demo.service.dao.impl;

import com.txy.sw_demo.bean.User;
import com.txy.sw_demo.service.dao.UserDAO;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/5 10:19
 */
@Component
public class EsUserDAOImpl implements UserDAO {
    @Value("${monitor.es.address}")
    private String esAddress;
    String index = "user";
    String type = "_doc";

    private RestHighLevelClient defaultClient(){
            String[] strs = esAddress.split(":");
            String ip = strs[0];
            int port = Integer.valueOf(strs[1]);

            return new RestHighLevelClient(
                        RestClient.builder(
                            new HttpHost(ip, port, "http"),
                            new HttpHost(ip, port + 1, "http")));
    }


    @Override
    public Object add(User user) {
        IndexRequest indexRequest = new IndexRequest(index, type).source("name", user.getName(), "age", user.getAge());
        try(RestHighLevelClient client = defaultClient()
        ) {
            client.index(indexRequest);
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
        List<String> result = new ArrayList<>();
        try ( RestHighLevelClient client = defaultClient())
        {
            SearchResponse searchResponse = client.search(searchRequest);

            SearchHits hits = searchResponse.getHits();
            for(SearchHit hit : hits){
                String sourceAsString = hit.getSourceAsString();
                result.add(sourceAsString);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object delete(User user) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();  // 默认配置
        sourceBuilder.query(QueryBuilders.termQuery("name", user.getName())); // 设置搜索，可以是任何类型的 QueryBuilder
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS)); // 设置搜索的超时时间
        searchRequest.source(sourceBuilder);

        List<String> idList = new ArrayList<>();
        try (RestHighLevelClient client = defaultClient())
        {
            SearchResponse searchResponse = client.search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            for(SearchHit hit : hits){
                idList.add(hit.getId());
            }

            BulkRequest bulkRequest = new BulkRequest();
            for(String id : idList){
                DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
                bulkRequest.add(deleteRequest);
            }
            BulkResponse bulkItemResponses = client.bulk(bulkRequest);

        }catch (IOException e){
            e.printStackTrace();
        }

        return user;
    }
}