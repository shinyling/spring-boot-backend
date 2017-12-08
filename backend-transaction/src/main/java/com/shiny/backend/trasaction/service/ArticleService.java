package com.shiny.backend.trasaction.service;

import com.shiny.backend.mybatis.entity.Article;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shiny
 **/
public interface ArticleService {

    /**
     * 测试事务
     * @param article article
     * @throws Exception 异常
     * @return user
     */
    Article save(Article article) throws Exception;
}
