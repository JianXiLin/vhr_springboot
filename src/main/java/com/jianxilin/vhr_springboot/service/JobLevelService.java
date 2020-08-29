package com.jianxilin.vhr_springboot.service;


import com.jianxilin.vhr_springboot.mapper.JobLevelMapper;
import com.jianxilin.vhr_springboot.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    public Integer addJobLevel(JobLevel jobLevel) {
        jobLevel.setEnabled(true);
        jobLevel.setCreateDate(new Date());
        return jobLevelMapper.insertSelective(jobLevel);
    }

    public Integer updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    public Integer deletePostion(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer multipleDeleteJobLevel(Integer[] ids) {
        return jobLevelMapper.multipleDeleteJobLevel(ids);
    }
}