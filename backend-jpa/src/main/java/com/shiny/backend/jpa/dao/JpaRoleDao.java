package com.shiny.backend.jpa.dao;

import com.shiny.backend.jpa.entity.JpaRole;
import org.springframework.data.repository.CrudRepository;

/**
 * @author shiny
 */
public interface JpaRoleDao extends CrudRepository<JpaRole,String> {

}
