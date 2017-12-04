package com.shiny.backend.trasaction.service;

import com.shiny.backend.mybatis.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void save() throws Exception {
        Article article=new Article();
        article.setId("1");
        article.setName("test");
        article.setDescription("test test");
        articleService.save(article);
    }

}