package com.jianxilin.vhr_springboot.controller.system.basic;

import com.jianxilin.vhr_springboot.model.JobLevel;
import com.jianxilin.vhr_springboot.model.JobLevel;
import com.jianxilin.vhr_springboot.model.ResponseBean;
import com.jianxilin.vhr_springboot.service.JobLevelService;
import com.jianxilin.vhr_springboot.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author 54683
 */
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {
    @Autowired
    JobLevelService JobLevelService;

    @GetMapping("/")
    public ResponseBean getAllPositons() {
        return ResponseBean.success("获取成功", JobLevelService.getAllJobLevels());
    }

    @PostMapping("/")
    public ResponseBean addJobLevel(@RequestBody JobLevel JobLevel) {
        if (JobLevelService.addJobLevel(JobLevel) != 1) {
            return ResponseBean.fail("添加失败");
        }
        return ResponseBean.success("添加成功");
    }

    @PutMapping("/")
    public ResponseBean updateJobLevel(@RequestBody JobLevel JobLevel) {
        if (JobLevelService.updateJobLevel(JobLevel) != 1) {
            return ResponseBean.fail("更新失败");
        }
        return ResponseBean.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteJobLevel(@PathVariable("id") Integer id) {
        if (JobLevelService.deletePostion(id) != 1) {
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }

    @DeleteMapping("/")
    public ResponseBean multipleDeleteJobLevel(Integer[] ids) {
        if (JobLevelService.multipleDeleteJobLevel(ids) < 1) {
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }
}
