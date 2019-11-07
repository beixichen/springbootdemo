package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CorsConfig extends WebMvcConfigurationSupport
{
       //继承WebMvcConfigurationSupport时，静态资源失效，需要重新配置静态资源路径
       @Override
       public void addResourceHandlers(ResourceHandlerRegistry registry) {
              registry.addResourceHandler("/**").addResourceLocations(
                      "classpath:/static/");
              registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                      "classpath:/META-INF/resources/");
              registry.addResourceHandler("/webjars/**").addResourceLocations(
                      "classpath:/META-INF/resources/webjars/");
              super.addResourceHandlers(registry);
       }
       @Override
       public void addCorsMappings(CorsRegistry registry) {
              registry
                      .addMapping("/**")
                      .allowedMethods("*")
                      .allowedOrigins("*")
                      .allowedHeaders("*");

              super.addCorsMappings(registry);

       }
}

