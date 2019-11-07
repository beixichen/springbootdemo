package com.example.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="fanout.c")
public class FanoutCConsumer {
       @RabbitHandler
       public void received(String msg){
              System.out.println("[fanout.c] received msg :"+msg);
       }
}
