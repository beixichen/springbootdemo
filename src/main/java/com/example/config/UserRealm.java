package com.example.config;

import com.example.domain.User;
import com.example.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

       @Autowired
       UserService userService;

       //授权
       @Override
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
              System.out.println("授权");
              SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
              //在数据库添加授权的字符串，放入add即可
              info.addStringPermission("user:add");
              return info;
       }


       //执行认证
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
              System.out.println("执行认证逻辑");
              String userName ="arthur";
              String userPassword="csas";
              //判断用户名
              UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
              User user = userService.getUserByName(token.getUsername());

              if(user==null){
                     return null;
              }
              //判断密码
              //第一个参数是传递的user，第二个为数据库的密码
              return new SimpleAuthenticationInfo("",user.getUserPassword(),"");

       }
}
