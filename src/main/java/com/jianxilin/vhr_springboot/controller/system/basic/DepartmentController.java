package com.jianxilin.vhr_springboot.controller.system.basic;

import com.jianxilin.vhr_springboot.model.Department;
import com.jianxilin.vhr_springboot.model.ResponseBean;
import com.jianxilin.vhr_springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public ResponseBean getAllDepartment(){
       List<Department> departmentList = departmentService.getAllDepartments();
        if (departmentList == null) {
            return ResponseBean.fail("获取失败");
        }
        return ResponseBean.success("获取成功",departmentList);
    }

    @PostMapping("/")
    public ResponseBean addDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
        if (department.getResult() !=1){
            return ResponseBean.fail("添加失败");
        }
        return ResponseBean.success("添加成功",department);
    }

    @DeleteMapping("/{depId}")
    public ResponseBean deleteDepartment(@PathVariable("depId") Integer depId){
        Department department = new Department();
        department.setId(depId);
        departmentService.deleteDepartment(department);
        if(department.getResult() == -2){
            return ResponseBean.fail("该部门下含有子部门，删除失败");
        }else if (department.getResult() == -1){
            return ResponseBean.fail("该部门下含有员工，删除失败");
        }else if (department.getResult() != 1){
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }
}
