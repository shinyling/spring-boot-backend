package com.shiny.backend.jpa.dao;

import com.shiny.backend.jpa.entity.JpaUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class JpaUserDaoTest {

    @Autowired
    private JpaUserDao jpaUserDao;

    @Before
    public void  testSave(){
        JpaUser jpaUser=new JpaUser();
        jpaUser.setId("1");
        jpaUser.setUsername("shiny");
        jpaUser.setPassword("11112asd1231231");
        jpaUser.setEmail("shiny123400@163.com");
        jpaUser.setLocked(false);
        jpaUser.setLastPasswordResetDate(new Date());
        JpaUser saved=jpaUserDao.save(jpaUser);
        assertNotNull(saved);
    }

    @Test
    public void testFindByUsername(){
        JpaUser jpaUser=jpaUserDao.findByUsername("shiny");
        assertNotNull(jpaUser);
    }
}