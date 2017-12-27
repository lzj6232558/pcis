package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    //视图跳转
    @RequestMapping("view")
    public String input() {
        return "role/role";
    }

    //查询
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("query")
    public List<Role> query() {
        return roleService.selectAll();
    }

    //添加或修改
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Role entity) {
        if (entity.getId() == null) {
            //添加:
            roleService.insert(entity);
        } else {
            //修改:
            roleService.updateByPrimaryKey(entity);
        }
        return new JsonResult(true,"保存成功!");
    }

    //添加或修改
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("delete")
    public JsonResult delete(Long id) {
        roleService.deleteByPrimaryKey(id);
        return new JsonResult(true,"删除成功!");
    }

    @RequestMapping("selectAllRole")
    @ResponseBody
    public List<Map<String,Object>> selectAllRole(){
        List<Map<String,Object>> roles = roleService.selectAllRole();
        return roles;
    }
}

