package com.shiny.backend.es.service;

import com.shiny.backend.es.entity.EsUser;

import java.util.List;

/**
 * @author shiny
 */
public interface EsUserService {

    /**
     * 添加用户
     * @param esUser esUser
     * @return
     */
    String saveUser(EsUser esUser);

    /**
     * 根据关键词分页查询
     * @param pageNum
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<EsUser> searchUser(Integer pageNum, Integer pageSize, String searchContent);

    /**
     * 根据username查询结果
     * @param username 用户名称
     * @return esUser list
     */
    List<EsUser> findByUsername(String username);
}
