package com.shiny.backend.es.dao;

import com.shiny.backend.es.entity.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author shiny
 **/
public interface EsUserDao extends ElasticsearchRepository<EsUser,String> {

    /**
     * 通过username 查询
     * @param username 用户名
     * @return esUser list
     */
    List<EsUser> findByUsername(String username);

}
