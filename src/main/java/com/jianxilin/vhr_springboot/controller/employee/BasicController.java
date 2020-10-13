package com.jianxilin.vhr_springboot.controller.employee;

import com.jianxilin.vhr_springboot.Utils.POIUtils;
import com.jianxilin.vhr_springboot.model.*;
import com.jianxilin.vhr_springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/emp/basic")
public class BasicController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 分页获取员工信息
     *
     * @param page
     * @param size
     * @param keyWork
     * @return
     */
    @GetMapping("/")
    private ResponseBean getEmployeesByPage(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            String keyWork) {
        ResponsePageBean responsePageBean = employeeService.getEmployeesByPage(page, size, keyWork);
        if (responsePageBean == null) {
            return ResponseBean.fail("获取失败");
        }
        return ResponseBean.success("获取成功", responsePageBean);
    }

    /**
     * 添加员工信息
     *
     * @param employee
     * @return
     */
    @PostMapping("/")
    private ResponseBean insertEmployee(@RequestBody Employee employee) {
        Integer result = employeeService.insertEmployee(employee);
        if (result != 1) {
            return ResponseBean.fail("添加失败");
        }
        return ResponseBean.success("添加成功");
    }

    /**
     * 获取职称
     *
     * @return
     */
    @GetMapping("/position/list")
    private List<Position> getAllPosition() {
        List<Position> positions = employeeService.getAllPosition();
        return positions;
    }

    /**
     * 获取政治面貌
     *
     * @return
     */
    @GetMapping("/politics/list")
    private List<Politicsstatus> getAllPoliticsStatus() {
        List<Politicsstatus> politicsStatus = employeeService.getAllPoliticsStatus();
        return politicsStatus;
    }

    /**
     * 获取民族列表
     *
     * @return
     */
    @GetMapping("/nation/list")
    private List<Nation> getAllNation() {
        List<Nation> nations = employeeService.getAllNation();
        return nations;
    }


    @GetMapping("/joblevel/list")
    private List<JobLevel> getAllJobLevel() {
        List<JobLevel> jobLevels = employeeService.getAllJobLevel();
        return jobLevels;
    }

    /**
     * 获取部门
     *
     * @return
     */
    @GetMapping("/department/list")
    private List<Department> getAllDepartment() {
        List<Department> departments = employeeService.getAllDepartment();
        return departments;
    }

    @DeleteMapping("/{id}")
    private ResponseBean delDepartment(@PathVariable("id") Integer id) {
        if (employeeService.delDepartment(id) != 1) {
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }

    @PutMapping("/")
    public ResponseBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee) != 1) {
            return ResponseBean.fail("更新失败");
        }
        return ResponseBean.success("更新成功");
    }

    @GetMapping("export")
    public ResponseEntity<byte[]> exportExcel(){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return POIUtils.employee2Excel(allEmployees);
    }

    @PostMapping("inport")
    public ResponseBean inputExcel(MultipartFile file) throws IOException {
        file.transferTo(new File("D:\\file.xls"));
        return ResponseBean.success("上传成功");
    }
}
