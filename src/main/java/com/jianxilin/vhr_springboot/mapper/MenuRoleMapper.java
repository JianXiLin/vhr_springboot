package com.jianxilin.vhr_springboot.mapper;

import com.jianxilin.vhr_springboot.model.MenuRole;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    Boolean deleteByRoleId(Integer rId);

    Integer insertRecord(@Param("rId") Integer rId,@Param("mIds") Integer[] mIds);
}