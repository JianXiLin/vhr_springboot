package com.jianxilin.vhr_springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class DataTest {

    @Test
    public void test(){
        new Date(1998,8,1);
    }

}
