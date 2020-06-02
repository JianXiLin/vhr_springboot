package com.jianxilin.vhr_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jianxilin.vhr_springboot.mapper")
public class VhrSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(VhrSpringbootApplication.class, args);
    }

}
