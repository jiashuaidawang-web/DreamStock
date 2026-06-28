package com.dreamstock.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dreamstock")
@MapperScan("com.dreamstock.infrastructure.mapper")
public class DreamStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamStockApplication.class, args);
    }
}
