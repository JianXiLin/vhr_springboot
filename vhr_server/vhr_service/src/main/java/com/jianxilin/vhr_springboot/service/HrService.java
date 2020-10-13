package com.jianxilin.vhr_springboot.service;

import com.jianxilin.vhr_springboot.mapper.HrMapper;
import com.jianxilin.vhr_springboot.mapper.HrRoleMapper;
import com.jianxilin.vhr_springboot.mapper.RoleMapper;
import com.jianxilin.vhr_springboot.model.Hr;
import com.jianxilin.vhr_springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null){
            throw new UsernameNotFoundException("用户名不存在");
        }else {
            hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        }
        return hr;
    }

    public List<Hr> getAllHrs(String keywork) {
        // Integer id = HrUtils.getCurrentHr().getId();
        return hrMapper.getAllHrs(keywork);
    }

    public Integer updateHr(Hr hr) {

        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    public Integer deleteHr(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Transactional
    public boolean updateHrRoles(Integer hId, Integer[] roleIds) {
        hrRoleMapper.deleteByHrId(hId);
        hrRoleMapper.insertRoles(hId,roleIds);
        return hrRoleMapper.insertRoles(hId,roleIds) == roleIds.length;
    }
}
