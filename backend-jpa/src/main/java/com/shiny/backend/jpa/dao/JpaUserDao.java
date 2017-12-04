package com.shiny.backend.jpa.dao;

import com.shiny.backend.jpa.entity.JpaUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author DELL
 */
public interface JpaUserDao extends CrudRepository<JpaUser,String> {

    JpaUser findByUsername(String username);

}
