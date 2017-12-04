package com.shiny.backend.trasaction.service;

import com.shiny.backend.mybatis.dao.ArticleDao;
import com.shiny.backend.mybatis.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shiny
 **/
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article save(Article article) throws Exception {
        int count=articleDao.saveArticle(article);
        throw new Exception("报错");
    }
}
