package com.learning.json;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author mawandong
 * @date 2018/3/1 0:02
 */
public class logTest01 {
    private static final Logger logger=LoggerFactory.getLogger(logTest01.class);
    Gson gson=new Gson();

    @Before
    public void before(){

    }

    @Test
    public void test01(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("1","123");
        System.out.println(gson.toJson(map));
        logger.info(gson.toJson(map));
    }

    @Test
    public void test03(){
        logger.trace(gson.toJson(this.random()));
        logger.debug(gson.toJson(this.random()));
        logger.info(gson.toJson(this.random()));
        logger.warn(gson.toJson(this.random()));
        logger.error(gson.toJson(this.random()));
    }


    public List<Integer> random(){

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<100;i++){
           Integer a= ThreadLocalRandom.current().nextInt(0,100);
           list.add(a);
        }
        return list;

    }
}
