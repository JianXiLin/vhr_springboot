package com.jianxilin.vhr_springboot.service;

import com.jianxilin.vhr_springboot.mapper.MenuMapper;
import com.jianxilin.vhr_springboot.mapper.MenuRoleMapper;
import com.jianxilin.vhr_springboot.model.Hr;
import com.jianxilin.vhr_springboot.model.Menu;
import com.jianxilin.vhr_springboot.model.MenuRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenusByHrId() {

        return menuMapper.getMenusByHrId(
                ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    public List<Menu> getAllMenuWithRole() {
        return menuMapper.getAllMenuWithRole();
    }

    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    public List<Integer> getMenuIdsByRoleId(Integer rId) {
        return menuMapper.getMenuIdsByRoleId(rId);
    }

    @Transactional
    public boolean updateMenuForRole(Integer rId, Integer[] mIds) {
        menuRoleMapper.deleteByRoleId(rId);
        Integer result = menuRoleMapper.insertRecord(rId, mIds);
        return result == mIds.length;
    }
}
