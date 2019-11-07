package com.example.springbootdemo;

import com.example.domain.User;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {
       Logger logger = LoggerFactory.getLogger(this.getClass());
       @Test
       public void contextLoads() {

       }

       @Autowired
       private UserService userService;
       @Test
       public void selectUserByIdTest(){
              User user = userService.selectUserById(10);
              logger.info("查找结果" + user);
       }

       @Test
       public void test(){
              User user=userService.test(1);
              System.out.println(user);
       }

       @Autowired
       private RedisTemplate redisTemplate;
       @Test
       public void redisTest() {
              // redis存储数据
              String key = "name";
              redisTemplate.opsForValue().set(key, "yukong");
              // 获取数据
              String value = (String) redisTemplate.opsForValue().get(key);
              System.out.println("获取缓存中key为" + key + "的值为：" + value);

              User user = new User();
              user.setUserName("yukong");
              String userKey = "yukong";
              redisTemplate.opsForValue().set(userKey, user);
              User newUser = (User) redisTemplate.opsForValue().get(userKey);
              System.out.println("获取缓存中key为" + userKey + "的值为：" + newUser);

       }


}
