package com.jianxilin.vhr_springboot.mapper;

import com.jianxilin.vhr_springboot.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployees(@Param("start") Integer start, @Param("size") Integer size,@Param("keyWork") String keyWork);

    Integer getTotal(String keyWork);
}