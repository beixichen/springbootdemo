package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.domain.User;
import com.example.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
       @Autowired
       private UserService userService;

       @RequestMapping(value = "/",method = RequestMethod.GET)
       public String getIndex(Model model){
              User user = userService.selectUserById(1);
              model.addAttribute("user", user);
              return "index";
       }
       @RequestMapping(value="getAll",method =RequestMethod.GET)
       public List<User> getAll()
       {
              List<User> list=userService.getAll();
              return list;
       }
       @GetMapping(value = "getUserByName",params = {"name"})
       @ResponseBody
       public User getUserByName(String name){
              User user = userService.getUserByName(name);
              return user;
       }
      @RequestMapping(value = "getUserById",method = RequestMethod.GET)
      @ResponseBody
      @ApiImplicitParams(
              @ApiImplicitParam(name = "userid",value="商品ID",dataType = "Integer",paramType = "query")
      )
       public String getUserById(Integer userid){
              User user= userService.getUserById(userid);
              JSONObject jsonObject = new JSONObject();
              System.out.println(jsonObject.toJSONString(user));
              return jsonObject.toJSONString(user);
       }
       @GetMapping(value = "test",params = {"num"})
       @ResponseBody
       public User Test(Integer num){
              User res =  userService.getUserById(num);
              return res;
       }
       @GetMapping(value="tt/{input}")
       @ResponseBody
       public User TT(@PathVariable Integer input){
              User res = userService.getUserById(input);
              return res;
       }
       @GetMapping
       @ResponseBody
       public User T(@RequestBody Integer input){
              User user = userService.getUserById(input);
              return user;
       }
       @GetMapping("/redisTest")
       public User redisGetUser(Integer id){
              User user = userService.getUserRedis(id);
              return user;
       }
       @RequestMapping("/toLogin")
       public String toLogin(){
              return "/login";
       }
       @RequestMapping("/noAuth")
       public String noAuth(){
              return "noAuth";
       }
       @RequestMapping("/login")
       public String login(String name ,String password, Model model){
              System.out.println("开始认证操作");
              //认证操作
              Subject subject= SecurityUtils.getSubject();
              UsernamePasswordToken token= new UsernamePasswordToken(name ,password);
             try{
                    subject.login(token);
                    return "index";
             } catch (UnknownAccountException error){
                    System.out.println("123");
                    model.addAttribute("msg","账户不存在，登录失败");
                    return "login";
             }catch (IncorrectCredentialsException e){
                    model.addAttribute("msg","密码错误，登录失败");
                    return "login";
             }

       }

}
