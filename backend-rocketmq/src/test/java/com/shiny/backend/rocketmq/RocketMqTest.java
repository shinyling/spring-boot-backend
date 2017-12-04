package com.shiny.backend.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shiny
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketMqTest {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void testSend() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        defaultMQProducer.setVipChannelEnabled(false);
        Message message=new Message("TEST","TEST","test",("Hello RocketMq!!!").getBytes());
        SendResult sendResult=defaultMQProducer.send(message);
        System.out.println(sendResult);

    }
}
