package com.example.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="fanout.a")
public class FanoutAConsumer {
       @RabbitHandler
       public void received(String msg){
              System.out.println("[fanout.a] received msg :"+msg);
       }
}
