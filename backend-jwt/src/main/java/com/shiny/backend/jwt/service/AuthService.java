package com.shiny.backend.jwt.service;


import com.shiny.backend.common.entity.User;
import com.shiny.backend.jwt.entity.JwtUser;

/**
 * @author DELL
 */
public interface AuthService {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 带token用户
     */
    JwtUser login(String username, String password);

    /**
     * 刷新token
     * @param token 用户凭证
     * @return
     */
    String refresh(String token);

    /**
     * 用户注册
     * @param addUser 要注册的用户
     * @return 注册的用户
     */
    User register(User addUser);
}
