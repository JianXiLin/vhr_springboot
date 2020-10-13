package com.jianxilin.vhr_springboot.controller;

import com.jianxilin.vhr_springboot.model.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseBean Login(){
        return ResponseBean.fail("尚未登录");
    }
}
