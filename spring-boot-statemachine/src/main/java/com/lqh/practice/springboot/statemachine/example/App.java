package com.lqh.practice.springboot.statemachine.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

@SpringBootApplication
@EnableStateMachine
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
