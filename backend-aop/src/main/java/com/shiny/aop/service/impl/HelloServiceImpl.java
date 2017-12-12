package com.shiny.aop.service.impl;

import com.shiny.aop.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author shiny
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello() {
        return "hello";
    }
}
