package com.example.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
       //创建ShiroFilterFactoryBean
       @Bean
       public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
              ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
              shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
              //添加内置的过滤器
              /*
              anno: 无需认证
              authc: 必须认证
              user: 如果使用rememberMe功能可以直接访问
              perms： 资源必须得到资源权限
              role: 资源必须得到角色权限才可以访问
              * */
              Map<String,String> filterMap= new LinkedHashMap<>();//有序按put顺序

              filterMap.put("/user/getAll","authc");
              filterMap.put("/update","authc");
//              filterMap.put("/login","anno");
              filterMap.put("/user/test","perms[user:test]");
              shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
              shiroFilterFactoryBean.setUnauthorizedUrl("noAuth");
              shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
              return shiroFilterFactoryBean;
       }
       //创建DefaultWebSecurityManager
       @Bean(name="securityManager")
       public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
              DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
              defaultWebSecurityManager.setRealm(userRealm);
              return defaultWebSecurityManager;
       }
       //创建Realm
       @Bean(name = "userRealm")
       public UserRealm getUserRealm(){
              return new UserRealm();
       }
}
