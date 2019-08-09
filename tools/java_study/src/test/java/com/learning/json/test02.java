package com.learning.json;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author mawandong
 * @date 2019/1/6 0:52
 */
@Slf4j
public class test02 {

    Gson gson=new Gson();

    @Before
    public void before(){

    }

    @Test
    public void test01(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("1","123");
        System.out.println(gson.toJson(map));
        log.info(gson.toJson(map));
    }

    @Test
    public void test03(){
        log.trace(gson.toJson(this.random()));
        log.debug(gson.toJson(this.random()));
        log.info(gson.toJson(this.random()));
        log.warn(gson.toJson(this.random()));
        log.error(gson.toJson(this.random()));
    }


    public List<Integer> random(){

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<100;i++){
            Integer a= ThreadLocalRandom.current().nextInt(0,100);
            list.add(a);
        }
        return list;

    }


    @Test
    public void test04(){
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String,Object> map1=new HashMap<String,Object>();
        Map<String,Object> map2=new HashMap<String,Object>();
        Map<String,Object> map3=new HashMap<String,Object>();
        Map<String,Object> map4=new HashMap<String,Object>();
        Map<String,Object> map5=new HashMap<String,Object>();
        Map<String,Object> map6=new HashMap<String,Object>();

        map4.put("xxx", "xx");


        log.trace(gson.toJson(this.random()));
        log.debug(gson.toJson(this.random()));
        log.info(gson.toJson(this.random()));
        log.warn(gson.toJson(this.random()));
        log.error(gson.toJson(this.random()));
    }


}
