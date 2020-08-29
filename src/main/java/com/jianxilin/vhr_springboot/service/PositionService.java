package com.jianxilin.vhr_springboot.service;

import com.jianxilin.vhr_springboot.mapper.PositionMapper;
import com.jianxilin.vhr_springboot.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreatedate(new Date());
        return positionMapper.insertSelective(position);
    }

    public  Integer updatePosition(Position position){
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePostion(Integer positionId){
        return positionMapper.deleteByPrimaryKey(positionId);
    }

    public Integer multipleDeletePosition(Integer[] ids) {
        return positionMapper.multipleDeletePosition(ids);
    }
}
