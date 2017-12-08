package com.shiny.backend.disconf.controller;

import com.alibaba.fastjson.JSON;
import com.shiny.backend.disconf.remoteconfig.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiny
 **/
@RestController
@RequestMapping("read")
public class ReadController {

    @Autowired
    private RedisConfig redisConfig;

    @GetMapping("config")
    public String readHostAndPort(){
        System.out.println(JSON.toJSONString(redisConfig));
        return JSON.toJSONString(redisConfig);
    }
}
