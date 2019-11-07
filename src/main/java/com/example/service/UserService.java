package com.example.service;


import com.example.dao.UserDao;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames = "user")
public class UserService{

       @Autowired
       private UserDao userDao;
       private RedisTemplate redisTemplate;

       public User selectUserById(Integer userId) {
              return userDao.selectUserById(userId);
       }
       public User getUserById(Integer id ){return userDao.getUserById(id);}
//       @Cacheable(cacheNames = "getAll",key = "getAll")
       public List<User> getAll(){
              return userDao.getAll();
       }
       public User getUserByName(String name){
              return userDao.getUserByName(name);
       }
       public User getUserRedis(Integer id){
              String key= String.valueOf(id);
              boolean hasKey= redisTemplate.hasKey(key);
              if(hasKey){
                     User user = (User)redisTemplate.opsForValue().get(key);
                     return user;
              }
              User user = userDao.getUserById(id);
              redisTemplate.opsForValue().set(key,user,600, TimeUnit.SECONDS);
              return user;
       }
       public User test(Integer id){
              DemoQueueConsumer demoQueueConsumer=new DemoQueueConsumer();
              demoQueueConsumer.received("123");
              return userDao.getUserById(id);
       }
}