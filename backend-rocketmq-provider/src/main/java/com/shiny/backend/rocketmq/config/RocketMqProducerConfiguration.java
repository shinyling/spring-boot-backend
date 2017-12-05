package com.shiny.backend.rocketmq.config;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
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
public class RocketMqProducerConfiguration {

    private static final Logger logger= LogManager.getLogger(RocketMqProducerConfiguration.class);

    @Value("${rocketmq.producer.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.producer.instanceName}")
    private String instanceName;

    @Value("${rocketmq.producer.maxMessageSize}")
    private int maxMessageSize ; //4M

    @Value("${rocketmq.producer.sendMsgTimeout}")
    private int sendMsgTimeout ;

    @Bean
    public DefaultMQProducer getRocketMQProducer(){
        if (StringUtils.isBlank(this.groupName)) {
            logger.error("groupName is blank");
            return null;
        }
        if (StringUtils.isBlank(this.namesrvAddr)) {
            logger.error("nameServerAddr is blank");
            return null;
        }
        if (StringUtils.isBlank(this.instanceName)){
            logger.error("instanceName is blank");
            return null;
        }
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setInstanceName(instanceName);
        producer.setMaxMessageSize(this.maxMessageSize);
        producer.setSendMsgTimeout(this.sendMsgTimeout);
        try {
            producer.start();
            logger.info("producer is start ! groupName:{},namesrvAddr:{}", this.groupName, this.namesrvAddr);
        } catch (Exception e) {
            logger.error("producer is error {} {}", e.getMessage(),e);
        }
        return producer;
    }
}
