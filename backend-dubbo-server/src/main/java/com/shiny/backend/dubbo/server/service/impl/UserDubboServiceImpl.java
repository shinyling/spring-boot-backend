package com.shiny.backend.dubbo.server.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shiny.backend.common.entity.User;
import com.shiny.backend.common.service.UserDubboService;

/**
 * @author shiny
 **/
@Service(version = "1.0.0")
public class UserDubboServiceImpl implements UserDubboService {

    @Override
    public User findByName(String username) {
        User user=new User();
        user.setId("11");
        user.setUsername(username);
        user.setPassword("12312");
        return user;
    }

}
