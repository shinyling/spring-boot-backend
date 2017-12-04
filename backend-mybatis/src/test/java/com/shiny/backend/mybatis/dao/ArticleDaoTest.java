package com.shiny.backend.mybatis.dao;

import com.shiny.backend.mybatis.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void saveArticle() throws Exception {
        Article article=new Article();
        article.setId(UUID.randomUUID().toString());
        article.setName("十万个为什么");
        article.setDescription("十万个为什么");
        int count=articleDao.saveArticle(article);
        assertTrue(count>0);
    }

    @Test
    public void findByName() throws Exception {
        Article article=articleDao.findByName("十万个为什么");
        assertNotNull(article);
    }

}