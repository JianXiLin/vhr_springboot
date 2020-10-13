package com.jianxilin.vhr_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String salary(){
        return "/employee/advanced/hello";
    }

    @GetMapping("/employee/basic/hello")
    public String system(){
        return "/employee/basic/hello";
    }
}
