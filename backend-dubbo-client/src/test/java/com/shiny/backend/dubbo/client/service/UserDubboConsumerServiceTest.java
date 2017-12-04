package com.shiny.backend.dubbo.client.service;

import com.shiny.backend.common.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDubboConsumerServiceTest {

    Logger logger= LogManager.getLogger(UserDubboConsumerServiceTest.class);

    @Autowired
    private UserDubboConsumerService userDubboConsumerService;

    @Test
    public void findByUserName() throws Exception {
        User user=userDubboConsumerService.findByUserName("shiny");
        logger.info(user);
        assertNotNull(user);
    }

}