package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
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
        result.add("ALL");//  黑色什么什么 统计数据
        result.add("黑色每日统计");//  黑色什么什么 统计数据
        result.add("焦煤每日统计"); // 焦煤的什么什么统计数据
        result.add("焦煤和黑色每日差值");
        return result;

    }

    //查询
    @RequestMapping("/query")
    @ResponseBody
    public List query(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
//        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//        List<Map> targetList = (List) params.get("targets");
//        for (Map targetMap : targetList) {
//            String target = (String) targetMap.get("target");
//            //Map scopedVars = (Map) params.get("scopedVars");
//            //Map IP = (Map) scopedVars.get("IP");
//            //String nodeIP = (String) IP.get("text");
//            if (target.equals("CPU")) {
//                result.add(strjson());//放入json数据
//            } else if (target.equals("RAM")) {
//                result.add(null);//放入json数据
//            } else if (target.equals("LOAD")) {
//                result.add(null);
//            } else if (target.equals("SWAP")) {
//                result.add(null);
//            } else if (target.equals("DISK")) {
//                result.add(null);
//            } else if (target.equals("NET")) {
//                result.add(null);
//            }
//        }
//        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
//        response.setHeader("Access-Control-Allow-Methods", "POST");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Accept", "application/json");
//        response.setHeader("Content-Type", "application/json");
//        response.setHeader("Authorization", "Bearer eyJrIjoidGFIOW41aHRoMXZ5aTkxcDBNYUxteDk3TU9zVUhoSkUiLCJuIjoiZ3JhZmFuYV90ZXN0IiwiaWQiOjF9");
//        Collections.sort(result, (o1, o2) -> {
//            String name1 = String.valueOf(o1.get("target").toString());
//            String name2 = String.valueOf(o2.get("target").toString());
//            return name1.compareTo(name2);
//        });
//        return result;

        DateTime now = DateTime.now();

        HashMap<String, Object> map = Maps.newHashMap();

        map.put("target","黑色每日统计");


        List<ArrayList<? extends Number>> collect = IntStream.rangeClosed(1, 20)
                .boxed()
                .map(integer -> Lists.newArrayList(integer * 10, now.minusHours(integer).getMillis()))
                .collect(Collectors.toList());
        map.put("datapoints",collect);


        HashMap<String, Object> map1 = Maps.newHashMap();

        map1.put("target","焦煤每日统计");


        List<ArrayList<? extends Number>> collect1 = IntStream.rangeClosed(1, 20)
                .boxed()
                .map(integer -> Lists.newArrayList(integer * 10+50, now.minusHours(integer).getMillis()))
                .collect(Collectors.toList());
        map1.put("datapoints",collect1);

        return Lists.newArrayList(map,map1);
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
