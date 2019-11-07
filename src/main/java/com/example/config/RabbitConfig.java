package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
       @Bean
       public Queue demoString(){
              return new Queue("demoQueue");
       }
       //=====广播模式=====
       /*将三个队列绑定到一个交换机上，只要往这个交换机上发送消息，
       * 绑定上的队列都可以收到
       * */
       @Bean
       public Queue fanoutA(){
              return new Queue("fanout.a");

       }
       @Bean
       public Queue fanoutB(){
              return new Queue("fanout.b");
       }
       @Bean
       public Queue fanoutC(){
              return new Queue("fanout.c");
       }

       @Bean
       FanoutExchange fanoutExchange(){
              return new FanoutExchange("fanoutExchange");
       }

       @Bean
       public Binding bindingExchangeWithA(){
              return BindingBuilder.bind(fanoutA()).to(fanoutExchange());
       }

       @Bean
       public Binding bindingExchangeWithB(){
              return BindingBuilder.bind(fanoutB()).to(fanoutExchange());
       }

       @Bean
       public Binding bindingExchangeWithC(){
              return BindingBuilder.bind(fanoutC()).to(fanoutExchange());
       }

       //===topic 模式===
       /*进行模糊匹配
       * */
       @Bean
       public Queue topiocA() {
              return new Queue("topic.a");
       }

       @Bean
       public Queue topicB() {
              return new Queue("topic.b");
       }

       @Bean
       public Queue topicC() {
              return new Queue("topic.c");
       }

       /**
        * 定义个topic交换器
        * @return
        */
       @Bean
       TopicExchange topicExchange() {
              // 定义一个名为fanoutExchange的fanout交换器
              return new TopicExchange("topicExchange");
       }

       @Bean
       public Binding bindingTopicExchangeWithA() {
              return BindingBuilder.bind(topiocA()).to(topicExchange()).with("topic.msg");
       }

       /**
        * 将定义的topicB队列与topicExchange交换机绑定
        * @return
        */
       @Bean
       public Binding bindingTopicExchangeWithB() {
              return BindingBuilder.bind(topicB()).to(topicExchange()).with("topic.#");
       }

       /**
        * 将定义的topicC队列与topicExchange交换机绑定
        * @return
        */
       @Bean
       public Binding bindingTopicExchangeWithC() {
              return BindingBuilder.bind(topicC()).to(topicExchange()).with("topic.*.z");
       }


}
