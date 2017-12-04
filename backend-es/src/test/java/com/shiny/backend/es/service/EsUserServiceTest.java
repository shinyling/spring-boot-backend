package com.shiny.backend.es.service;

import com.shiny.backend.es.entity.EsUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EsUserServiceTest {

    @Autowired
    private EsUserService esUserService;

    @Test
    public void saveUser() throws Exception {
        EsUser esUser=new EsUser();
        esUser.setId("1");
        esUser.setUsername("shiny");
        esUser.setPassword("123");
        esUser.setEmail("shiny123400@163.com");
        assertSame("1",esUserService.saveUser(esUser));
    }

    @Test
    public void searchUser() throws Exception {
        Integer pageNum=1,pageSize=10;
        String searchContent="shiny";
        List<EsUser> esUsers=esUserService.searchUser(pageNum,pageSize,searchContent);
        assertTrue(esUsers.size()>0);
    }

    @Test
    public void findByUsername() throws Exception {
        String username="shiny";
        List<EsUser> esUsers=esUserService.findByUsername(username);
        assertTrue(esUsers.size()>0);
    }

}