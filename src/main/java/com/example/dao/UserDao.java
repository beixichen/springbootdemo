package com.example.dao;

import com.example.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
       User selectUserById(Integer userid);
       User getUserById(Integer id );
       User getUserByName(String name);
       List<User> getAll();
}
