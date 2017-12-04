package com.shiny.backend.es.service.impl;

import com.shiny.backend.es.dao.EsUserDao;
import com.shiny.backend.es.entity.EsUser;
import com.shiny.backend.es.service.EsUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author DELL
 */
@Service
public class EsUserServiceImpl implements EsUserService {

    private final static Logger logger= LogManager.getLogger(EsUserServiceImpl.class);

    /* 搜索模式 */
    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float  MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    @Autowired
    private EsUserDao esUserDao;

    @Override
    public String saveUser(EsUser esUser) {
        EsUser result=esUserDao.save(esUser);
        return result.getId();
    }

    @Override
    public List<EsUser> searchUser(Integer pageNum, Integer pageSize, String searchContent) {

        SearchQuery searchQuery=getUserSearchQuery(pageNum,pageSize,searchContent);

        logger.info("\n searchUser(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<EsUser> searchPageResults = esUserDao.search(searchQuery);
        return searchPageResults.getContent();
    }

    @Override
    public List<EsUser> findByUsername(String username) {
        return esUserDao.findByUsername(username);
    }

    /**
     * 未正确查询出结果
     * @param pageNum
     * @param pageSize
     * @param searchContent
     * @return
     */
    private SearchQuery getUserSearchQuery(Integer pageNum, Integer pageSize, String searchContent){
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("username", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.matchPhraseQuery("email", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(500))
                .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);

        Pageable pageable = new PageRequest(pageNum, pageSize);

        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }
}
