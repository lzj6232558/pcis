package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;


    //视图:
    @RequestMapping("view")
    public String view() {
        return "/department/view";
    }

    //查询数据:
    @ResponseBody
    @RequestMapping("query")
    public List<Department> query() {
        return departmentService.selectAll();
    }

    //添加或修改数据:
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Department entity) {
        if (entity.getId() == null) {
            departmentService.insert(entity);
        } else {
            departmentService.updateByPrimaryKey(entity);
        }
        return new JsonResult(true, "保存成功!");
    }


    //停用部门:
    @ResponseBody
    @RequestMapping("changeState")
    @PermissionAnnotation("部门停用")
    @RequiresPermissions("permission:stopDept")
    public JsonResult changeState(Long id) {
        departmentService.changeState(id);
        return new JsonResult(true, "保存成功!");
    }


    //获取所有部门
    @ResponseBody
    @RequestMapping("selectAll")
    public List<Department> selectAll() {
        return departmentService.selectAll();
    }


}
