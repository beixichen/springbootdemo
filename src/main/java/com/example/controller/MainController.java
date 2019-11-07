package com.example.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("Test")
@Api("Some Test")
public class MainController {
       @RequestMapping(value="/hello",method = RequestMethod.GET)
       public String hello(){
              return "Hi";
       }


}
