package com.jianxilin.vhr_springboot.service;

import com.jianxilin.vhr_springboot.mapper.*;
import com.jianxilin.vhr_springboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    NationMapper nationMapper;

    @Autowired
    JobLevelMapper jobLevelMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    public ResponsePageBean getEmployeesByPage(Integer page, Integer size,String keyWork) {
        if (page == null && size == null) {
            return null;
        }
        Integer start = (page - 1) * size;
        List<Employee> employees = employeeMapper.getEmployees(start, size,keyWork);
        Integer total = employeeMapper.getTotal(keyWork);

        ResponsePageBean responsePageBean = new ResponsePageBean(employees, total);
        return responsePageBean;
    }

    public List<Employee> getAllEmployees(){
        return employeeMapper.getEmployees(null, null, null);
    }

    public Integer insertEmployee(Employee employee) {

        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("mm");


        return employeeMapper.insertSelective(employee);
    }

    public List<Politicsstatus> getAllPoliticsStatus() {
        return politicsstatusMapper.getAllPoliticsStatus();

    }

    public List<Position> getAllPosition() {
        List<Position> allPositions = positionMapper.getAllPositions();
        return allPositions;
    }

    public List<Nation> getAllNation() {
        return nationMapper.getAllNation();
    }

    public List<JobLevel> getAllJobLevel() {
        return jobLevelMapper.getAllJobLevels();
    }

    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public Integer delDepartment(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public int updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }
}
