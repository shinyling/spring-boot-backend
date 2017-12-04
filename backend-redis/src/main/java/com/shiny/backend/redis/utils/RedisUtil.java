package com.shiny.backend.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author shiny
 **/
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除key
     * @param keys key数组
     */
    public void remove(final String... keys){
        for(String key:keys){
            remove(key);
        }
    }

    /**
     * 删除key
     * @param key 键
     */
    public void remove(final String key){
        redisTemplate.delete(key);
    }

    /**
     * 批量删除key
     * @param pattern 正则
     */
    public void removePatter(final String pattern){
        Set<Serializable> keys= redisTemplate.keys(pattern);
        if(keys.size()>0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * 判断redis中是否存在对应的key
     * @param key 查询的key值
     * @return 是否存在
     */
    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key 缓存键值
     * @return 缓存value值
     */
    public Object get(final String key){
        Object result=null;
        ValueOperations<Serializable,Object> operations=redisTemplate.opsForValue();
        result=operations.get(key);
        return result;
    }

    /**
     * 写入缓存 带失效时间
     * @param key 键
     * @param value 值
     * @return 写入是否成功
     */
    public boolean set(final String key,Object value){
        boolean result=false;
        try {
            ValueOperations<Serializable,Object> operations=redisTemplate.opsForValue();
            operations.set(key,value);
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存 带失效时间
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间
     * @return 写入是否成功
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
