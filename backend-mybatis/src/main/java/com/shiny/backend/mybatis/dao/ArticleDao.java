package com.shiny.backend.mybatis.dao;

import com.shiny.backend.mybatis.entity.Article;
import org.apache.ibatis.annotations.*;

/**
 * @author shiny
 **/
@Mapper
public interface ArticleDao {

    /**
     * 保存文章
     * @param article article
     * @return 受影响行数
     */
    @Insert("insert into t_article values(#{id},#{name},#{description})")
    int saveArticle(Article article);
    /**
     * 通过name查找文章
     * @param name 名字
     * @return article
     */
    @Select("select * from t_article where name=#{name}")
    // 返回 Map 结果集
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description")
    })
    Article findByName(@Param("name") String name);
}
