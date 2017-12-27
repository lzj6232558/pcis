package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.utils.JsonResult;
import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    //视图跳转
    @RequestMapping("view")
    public String input() {
        return "permission/permission";
    }

    //查询
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("query")    //spring MVC前台访问后台的资源路径;
    public PageResult query(QueryObject qo) {
        return permissionService.query(qo);
    }

    //查询所有
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("getAll")    //spring MVC前台访问后台的资源路径;
    public List<Permission> getAll() {
        return permissionService.selectAll();
    }

    //添加或修改
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Permission entity) {
        if (entity.getId() == null) {
            //添加:
            permissionService.insert(entity);
        } else {
            //修改:
            permissionService.updateByPrimaryKey(entity);
        }
        return JSONUtils.toJSONString(new JsonResult(true,"成功!"));
    }

    //根据角色id查询权限:
    @ResponseBody               //把返回的数据转化为json字符串,并按照原路返回;
    @RequestMapping("getPermissionByRoleId")
    public List<Permission> getPermissionByRoleId(Long roleId) {
        return permissionService.getPermissionByRoleId(roleId);
    }


    //加载权限:
    @ResponseBody
    @RequestMapping("reload")
    public JsonResult reload() {
        permissionService.reload();
        return new JsonResult(true,"加载成功!");
    }


}

