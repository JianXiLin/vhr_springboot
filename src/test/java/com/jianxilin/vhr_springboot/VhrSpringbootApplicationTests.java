package com.jianxilin.vhr_springboot;

import com.jianxilin.vhr_springboot.mapper.MenuMapper;
import com.jianxilin.vhr_springboot.model.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VhrSpringbootApplicationTests {

    @Autowired
    MenuMapper menuMapper;

    @Test
    void contextLoads() {
        System.out.println("demo");
    }

    @Test
    void testMenu(){
        List<Menu> menus = menuMapper.getMenusByHrId(3);

    }
}
