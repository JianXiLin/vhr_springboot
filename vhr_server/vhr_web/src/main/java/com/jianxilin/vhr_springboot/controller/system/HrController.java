package com.jianxilin.vhr_springboot.controller.system;

import com.jianxilin.vhr_springboot.model.Hr;
import com.jianxilin.vhr_springboot.model.ResponseBean;
import com.jianxilin.vhr_springboot.model.Role;
import com.jianxilin.vhr_springboot.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {

    @Autowired
    HrService hrService;

    @GetMapping("/")
    public ResponseBean getAllHrs(String keywork) {
        List<Hr> allHrs = hrService.getAllHrs(keywork);
        if (allHrs == null) {
            return ResponseBean.fail("获取失败");
        }
        return ResponseBean.success("获取成功", allHrs);
    }

    @PutMapping("/")
    public ResponseBean updateHr(@RequestBody Hr hr) {

        Integer result = hrService.updateHr(hr);
        if (result != 1) {
            return ResponseBean.fail("修改失败");
        }
        return ResponseBean.success("修改成功");
    }

    @GetMapping("/roles")
    public ResponseBean getAllRoles() {
        List<Role> roles = hrService.getAllRoles();
        if (roles == null) {
            return ResponseBean.fail("获取失败");
        }
        return ResponseBean.success("获取成功", roles);
    }

    @PutMapping("/roles")
    public ResponseBean updateHrRoles(Integer hId, Integer[] roleIds) {
        if (!hrService.updateHrRoles(hId, roleIds)) {
            return ResponseBean.fail("更新失败");
        }
        return ResponseBean.success("更新成功");
    }

    @DeleteMapping("/{hrId}")
    public ResponseBean deleteHr(@PathVariable("hrId") Integer id) {
        if (hrService.deleteHr(id) != 1) {
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }
}
