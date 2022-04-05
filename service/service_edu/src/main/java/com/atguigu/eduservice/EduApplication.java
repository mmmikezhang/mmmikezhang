package com.atguigu.eduservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//启动类
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
//在eduservice启动类上添加注解，设置包的扫描规则，在com.atguigu包下的内容都能扫描到，交给springboot管理

public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
