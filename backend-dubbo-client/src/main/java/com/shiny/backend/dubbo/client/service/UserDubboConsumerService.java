package com.shiny.backend.dubbo.client.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shiny.backend.common.entity.User;
import com.shiny.backend.common.service.UserDubboService;
import org.springframework.stereotype.Component;

/**
 * @author shiny
 **/
@Component
public class UserDubboConsumerService {

    @Reference(version = "1.0.0")
    private UserDubboService userDubboService;

    public User findByUserName(String username){
        return userDubboService.findByName(username);
    }
}
