package com.example.controller;


import com.example.service.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {
       @Autowired
       private RabbitProducer rabbitProducer;
       @GetMapping("/sendDemoQueue")
       public Object sendDemoQueue(){
              rabbitProducer.sendDemoQueue();
              return "success";
       }

       @GetMapping("/sendFanout")
       public Object sendFanout(){
              rabbitProducer.sendFanout();
              return "fanout ok";
       }
       @GetMapping("/sendTopicTopicAB")
       public Object sendTopicTopicAB() {
              rabbitProducer.sendTopicTopicAB();
              return "success";
       }

       @GetMapping("/sendTopicTopicB")
       public Object sendTopicTopicB() {
              rabbitProducer.sendTopicTopicB();
              return "success";
       }

       @GetMapping("/sendTopicTopicBC")
       public Object sendTopicTopicBC() {
              rabbitProducer.sendTopicTopicBC();
              return "success";
       }
}
