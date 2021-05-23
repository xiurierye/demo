package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.mongodb.client.MongoDatabase;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping
public class MyFirstController {

    @Autowired
    private MongoTemplate mongoTemplate;

    //设置数据源
    @RequestMapping()
    @ResponseBody
    public Map list(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Accept", "application/json");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Authorization", "Bearer eyJrIjoidGFIOW41aHRoMXZ5aTkxcDBNYUxteDk3TU9zVUhoSkUiLCJuIjoiZ3JhZmFuYV90ZXN0IiwiaWQiOjF9");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "200 ok");
        return map;
    }


    //设置参数
    @RequestMapping("/search")
    @ResponseBody
    public List search(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Accept", "application/json");
        response.setHeader("Content-Type", "application/json");
        response.setHeader("Authorization", "Bearer eyJrIjoidGFIOW41aHRoMXZ5aTkxcDBNYUxteDk3TU9zVUhoSkUiLCJuIjoiZ3JhZmFuYV90ZXN0IiwiaWQiOjF9");

        List<String> result = new ArrayList<String>();
//
//        result.add("黑色每日统计");//  黑色什么什么 统计数据
//        result.add("焦煤每日统计"); // 焦煤的什么什么统计数据
//        result.add("焦煤和黑色每日差值");

//        MongoDatabase db = mongoTemplate.getDb();
//        db.listCollectionNames().forEach(System.out::println);


        Set<String> collectionNames = mongoTemplate.getCollectionNames();


        return Lists.newArrayList(collectionNames);

    }

    //查询
    @RequestMapping("/query")
    @ResponseBody
    public List query(@RequestBody GrafanaQueryRequest params, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {



        //目前之只支持table的數據
        List<HashMap<String, Object>> collect1 = params.getTargets().stream().map(target -> {
            HashMap<String, Object> map = Maps.newHashMap();

            map.put("target", target.getTarget());

            Query query = Query.query(new Criteria());
            List<ArrayList<Object>> collect = mongoTemplate.find(query, Map.class, target.getTarget())
                    .stream()
                    .map(item -> Lists.newArrayList(item.getOrDefault("volume", 0), ((Date) item.getOrDefault("date", null)).getTime()))
                    .collect(Collectors.toList());


            map.put("datapoints", collect);

            return map;

        }).collect(Collectors.toList());
        return collect1;

    }

    private Map<String, Object> strjson() throws JsonProcessingException {
//        //String str = "{\"A\":{\"tables\":[{\"columns\":[{\"text\":\"序列\",\"sort\":true,\"desc\":true,\"title\":\"序列\",\"style\":{\"alias\":\"\",\"colorMode\":null,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"decimals\":2,\"pattern\":\"/.*/\",\"thresholds\":[],\"type\":\"number\",\"unit\":\"short\"},\"hidden\":false,\"$$hashKey\":\"object:644\"},{\"text\":\"水果名称12\",\"title\":\"水果名称12\",\"style\":{\"alias\":\"\",\"colorMode\":null,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"decimals\":2,\"pattern\":\"/.*/\",\"thresholds\":[],\"type\":\"number\",\"unit\":\"short\"},\"hidden\":false,\"$$hashKey\":\"object:645\"},{\"text\":\"价钱\",\"title\":\"价钱\",\"style\":{\"alias\":\"\",\"colorMode\":null,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"decimals\":2,\"pattern\":\"/.*/\",\"thresholds\":[],\"type\":\"number\",\"unit\":\"short\"},\"hidden\":false,\"$$hashKey\":\"object:646\"},{\"text\":\"重量（kg）\",\"title\":\"重量（kg）\",\"style\":{\"alias\":\"\",\"colorMode\":null,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"decimals\":2,\"pattern\":\"/.*/\",\"thresholds\":[],\"type\":\"number\",\"unit\":\"short\"},\"hidden\":false,\"$$hashKey\":\"object:647\"},{\"text\":\"总价钱\",\"title\":\"总价钱\",\"style\":{\"alias\":\"\",\"colorMode\":null,\"colors\":[\"rgba(245, 54, 54, 0.9)\",\"rgba(237, 129, 40, 0.89)\",\"rgba(50, 172, 45, 0.97)\"],\"decimals\":2,\"pattern\":\"/.*/\",\"thresholds\":[],\"type\":\"number\",\"unit\":\"short\"},\"hidden\":false,\"$$hashKey\":\"object:648\"}],\"rows\":[[1,\"水果01\",4,2,8],[2,\"水果02\",5,2,10],[3,\"水果03\",6,2,12],[4,\"水果04\",7,2,14],[5,\"水果05\",8,2,16]]}]}}";
//        Map<String, Object> map = new HashMap<>();
//        JSONArray ja = new JSONArray();
//        for (int i = 0; i < 10; i++) {
//            JSONObject jb = new JSONObject();
//            jb.put("num", 10*i);
//            jb.put("timeserie", "2021-05-05T"+(11+i)+":01:01");
//            ja.add(jb);
//        }
//        map.put("reasult", ja);
//        return map;


        return null;

    }

    //添加注解
    @RequestMapping("/annotations")
    @ResponseBody
    public Map Annotations() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "200 ok");
        return map;
    }
}
