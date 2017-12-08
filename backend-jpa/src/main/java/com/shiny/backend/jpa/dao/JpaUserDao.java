package com.shiny.backend.jpa.dao;

import com.shiny.backend.jpa.entity.JpaUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author DELL
 */
public interface JpaUserDao extends CrudRepository<JpaUser,String> {

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    JpaUser findByUsername(String username);

}
