package com.jianxilin.vhr_springboot.controller.system.basic;

import com.jianxilin.vhr_springboot.model.Menu;
import com.jianxilin.vhr_springboot.model.MenuRole;
import com.jianxilin.vhr_springboot.model.ResponseBean;
import com.jianxilin.vhr_springboot.model.Role;
import com.jianxilin.vhr_springboot.service.MenuService;
import com.jianxilin.vhr_springboot.service.PermissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permission")
public class PermissController {
    @Autowired
    PermissService permissService;

    @Autowired
    MenuService menuService;

    @GetMapping("/roles")
    public ResponseBean getAllRoles() {
        List<Role> allRoles = permissService.getAllRoles();
        if (allRoles == null) {
            return ResponseBean.fail("获取失败");
        }
        return ResponseBean.success("获取成功", allRoles);
    }

    @GetMapping("/menu")
    public ResponseBean getAllMenu(){
        List<Menu> allMenu = menuService.getAllMenu();
        if (allMenu == null) {
            return ResponseBean.fail("获取失败");
        }
        return ResponseBean.success("获取成功", allMenu);
    }

    /**
     * 根据角色id 获取 该角色可操作的菜单id（mId）
     * @param rId 角色id
     * @return
     */
    @GetMapping("/mIds/{rId}")
    public ResponseBean getMidByRoleId(@PathVariable("rId") Integer rId){
        List<Integer> menuIdsByRoleId = menuService.getMenuIdsByRoleId(rId);
        if (menuIdsByRoleId == null) {
            return ResponseBean.fail("获取失败");
        }
        return ResponseBean.success("获取成功", menuIdsByRoleId);
    }

    @PutMapping("/")
    public ResponseBean updateMenuForRole(@RequestParam("rid") Integer rId,@RequestParam("mids") Integer[] mIds){
        if(!menuService.updateMenuForRole(rId,mIds)){
            return ResponseBean.fail("更新失败");
        }
        return ResponseBean.success("更新成功");
    }

    @PostMapping("/roles")
    public ResponseBean addRole(@RequestBody Role role){
        if (permissService.addRole(role) == 0) {
            return ResponseBean.fail("添加失败");
        }
        return ResponseBean.success("添加成功");
    }

    @DeleteMapping("/roles/{rid}")
    public ResponseBean deleteRole(@PathVariable("rid") Integer rid){
        if (permissService.deleteRole(rid) == 0) {
            return ResponseBean.fail("删除失败");
        }
        return ResponseBean.success("删除成功");
    }
}
