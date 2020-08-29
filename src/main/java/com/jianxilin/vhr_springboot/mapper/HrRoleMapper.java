package com.jianxilin.vhr_springboot.mapper;

import com.jianxilin.vhr_springboot.model.HrRole;
import org.apache.ibatis.annotations.Param;

public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    boolean deleteByHrId(Integer hId);

    Integer insertRoles(@Param("hId") Integer hId, @Param("rIds") Integer[] rIds);
}