package com.shiny.backend.redis.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void save( )  {
        String key="redisTest";
        String value="redisValue";
        assertTrue(redisUtil.set(key,value));
    }

    @Test
    public void saveWithExpireTime( )  {
        String key="redisTest";
        String value="redisValue";
        assertTrue(redisUtil.set(key,value,5L));
    }

    @Test
    public void get( ) {
        String key="redisTest";
        Object value=redisUtil.get(key);
        assertNotNull(value);
        System.out.println(JSON.toJSONString(value));
    }
}