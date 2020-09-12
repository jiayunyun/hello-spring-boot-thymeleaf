package com.jy.hello.spring.boot.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class HelloSpringBootThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringBootThymeleafApplication.class, args);
    }

}
