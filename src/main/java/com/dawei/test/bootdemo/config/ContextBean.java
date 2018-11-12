package com.dawei.test.bootdemo.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @author by Dawei on 2018/8/20
 */
@Configuration
public class ContextBean {


    @Value("${queueName}")
    private String queueName;

    /* 消息 管道队列  */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }
}
