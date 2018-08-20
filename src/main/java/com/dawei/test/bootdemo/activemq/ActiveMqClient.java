package com.dawei.test.bootdemo.activemq;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author by Dawei on 2018/8/20
 */
@Component
public class ActiveMqClient {

    @Resource
    private Queue queue;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void pushMessage() {

        String messageBody = "JSON";
        jmsMessagingTemplate.convertAndSend(queue, messageBody);
    }

}
