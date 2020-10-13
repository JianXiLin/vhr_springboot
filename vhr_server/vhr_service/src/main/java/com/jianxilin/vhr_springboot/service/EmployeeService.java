package com.jianxilin.vhr_springboot.service;

import com.jianxilin.vhr_springboot.mapper.*;
import com.jianxilin.vhr_springboot.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

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

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Autowired
    RabbitTemplate rabbitTemplate;


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
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        int result = employeeMapper.insertSelective(employee);
        if (result == 1) {
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            logger.info(emp.toString());
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, emp);
        }
        return result;
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
