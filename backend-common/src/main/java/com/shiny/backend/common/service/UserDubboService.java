package com.shiny.backend.common.service;

import com.shiny.backend.common.entity.User;

/**
 * @author shiny
 **/
public interface UserDubboService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByName(String username);
}
