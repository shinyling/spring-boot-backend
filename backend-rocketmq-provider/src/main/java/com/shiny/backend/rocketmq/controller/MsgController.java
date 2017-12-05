package com.shiny.backend.rocketmq.controller;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiny
 **/
@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private DefaultMQProducer producer;

    @GetMapping("send")
    public void sendMsg(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message=new Message("sms","verifycode","test",msg.getBytes());
        SendResult sendResult=producer.send(message);
        System.out.println(sendResult);
    }
}
