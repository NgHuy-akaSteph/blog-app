package com.huyquang.blogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.huyquang")
public class BlogAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }

}
