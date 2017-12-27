package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Attendance;
import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AttendanceQueryObject;
import cn.wolfcode.crm.service.IAttendanceSerivce;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("attendance")
public class AttendanceController {

    @Autowired
    private IAttendanceSerivce attendanceSerivce;

    @RequiresPermissions("attendance:view")
    @PermissionAnnotation("签到页面")
    @RequestMapping("view")
    public String view() {
        return "attendance/attendance";
    }


    @RequiresPermissions("attendance:list")
    @PermissionAnnotation("签到记录列表")
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(AttendanceQueryObject qo) {
        attendanceSerivce.checkState();
        return attendanceSerivce.query(qo);
    }


    @RequiresPermissions("attendance:deleteByPrimaryKey")
    @PermissionAnnotation("删除签到记录")
    @RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public JsonResult deleteByPrimaryKey(Long id) {
        try {
            attendanceSerivce.deleteByPrimaryKey(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("删除失败,请重新操作");
        }
    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Attendance record) {
        if (record.getId() == null) {
            attendanceSerivce.insert(record);
        } else {
            attendanceSerivce.updateByPrimaryKey(record);
        }
        return new JsonResult(true, "保存成功!");
    }


    @RequestMapping("signIn")
    @ResponseBody
    public JsonResult signIn(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        return attendanceSerivce.signIn(employee, request);
    }

    @RequiresPermissions("attendance:resign")
    @PermissionAnnotation("补签")
    @RequestMapping("resignIn")
    @ResponseBody
    public JsonResult resignIn(Long id) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        return attendanceSerivce.resignIn(employee, id);
    }


    @RequestMapping("signOut")
    @ResponseBody
    public JsonResult signOut() {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        return attendanceSerivce.signOut(employee);
    }
}
