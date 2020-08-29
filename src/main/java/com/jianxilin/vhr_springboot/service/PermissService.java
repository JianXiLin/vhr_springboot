package com.jianxilin.vhr_springboot.service;

import com.jianxilin.vhr_springboot.mapper.RoleMapper;
import com.jianxilin.vhr_springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissService {
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    public Integer deleteRole(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
