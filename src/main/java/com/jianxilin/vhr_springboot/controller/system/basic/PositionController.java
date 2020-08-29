package com.jianxilin.vhr_springboot.controller.system.basic;

import com.jianxilin.vhr_springboot.model.Position;
import com.jianxilin.vhr_springboot.model.ResponseBean;
import com.jianxilin.vhr_springboot.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public ResponseBean getAllPositons() {
        return ResponseBean.success("获取成功", positionService.getAllPositions());
    }

    @PostMapping("/")
    public ResponseBean addPosition(@RequestBody Position position) {
        if (positionService.addPosition(position) != 1) {
            return ResponseBean.fail("添加失败");
        }
        return ResponseBean.success("添加成功");
    }

    @PutMapping("/")
    public ResponseBean updatePosition(@RequestBody Position position) {
        if (positionService.updatePosition(position) != 1) {
            return ResponseBean.fail("更新失败");
        }
        return ResponseBean.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public ResponseBean deletePosition(@PathVariable("id") Integer id) {
        if (positionService.deletePostion(id) != 1) {
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }

    @DeleteMapping("/")
    public ResponseBean multipleDeletePosition(Integer[] ids) {
        if (positionService.multipleDeletePosition(ids) < 1) {
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }
}
