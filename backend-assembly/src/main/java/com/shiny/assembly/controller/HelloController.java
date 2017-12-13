package com.shiny.assembly.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiny
 **/
@RestController
public class HelloController {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private String port;

    @GetMapping("hello")
    public String hello(){
        return host+":"+port;
    }
}
