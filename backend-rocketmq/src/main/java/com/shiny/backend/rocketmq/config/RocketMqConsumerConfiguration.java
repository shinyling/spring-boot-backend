package com.shiny.backend.rocketmq.config;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.shiny.backend.rocketmq.listener.MessageListener;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shiny
 **/
@Configuration
public class RocketMqConsumerConfiguration {

    private static final Logger logger= LogManager.getLogger(RocketMqConsumerConfiguration.class);

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumer.groupName}")
    private String groupName;

    @Value("${rocketmq.consumer.topic}")
    private String topic;

    @Value("${rocketmq.consumer.tag}")
    private String tag;

    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;

    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;


    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer(){
        if (StringUtils.isBlank(groupName)){
            logger.error("groupName is null !!!");
            return null;
        }
        if (StringUtils.isBlank(namesrvAddr)){
            logger.error("namesrvAddr is null !!!");
            return null;
        }
        if (StringUtils.isBlank(topic)){
            logger.error("topic is null !!!");
            return null;
        }
        if (StringUtils.isBlank(tag)){
            logger.error("tag is null !!!");
            return null;
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setVipChannelEnabled(false);
        MessageListener messageListener = new MessageListener();
        consumer.registerMessageListener(messageListener);
        try {
            consumer.subscribe(topic,this.tag);
            consumer.start();
            logger.info("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}",groupName,topic,namesrvAddr);
        }catch (Exception e){
            logger.error("consumer is start !!! groupName:{},topic:{},namesrvAddr:{},{}",groupName,topic,namesrvAddr,e);
        }
        return consumer;
    }
}
