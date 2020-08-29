package com.jianxilin.vhr_springboot.service;

import com.jianxilin.vhr_springboot.mapper.DepartmentMapper;
import com.jianxilin.vhr_springboot.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
    }

    public void deleteDepartment(Department department) {
        departmentMapper.deleteDepartment(department);
    }
}
