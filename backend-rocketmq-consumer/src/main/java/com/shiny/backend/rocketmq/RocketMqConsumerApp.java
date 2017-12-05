package com.shiny.backend.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shiny
 **/
@SpringBootApplication
public class RocketMqConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqConsumerApp.class,args);
    }
}
