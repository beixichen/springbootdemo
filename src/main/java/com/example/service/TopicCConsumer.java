package com.example.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.c")
public class TopicCConsumer {

       /**
        * 消息消费
        * @RabbitHandler 代表此方法为接受到消息后的处理方法
        */
       @RabbitHandler
       public void received(String msg) {
              System.out.println("[topic.a] received message:" + msg);
       }
}