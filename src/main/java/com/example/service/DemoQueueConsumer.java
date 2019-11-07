package com.example.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "demoQueue")
public class DemoQueueConsumer {
       @RabbitHandler
       public void received(String msg){
              System.out.println("[demoQueue] received msg :"+msg);
       }
}
