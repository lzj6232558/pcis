package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Task;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ScheduleQueryObject;
import cn.wolfcode.crm.service.IScheduleService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private IScheduleService scheduleService;


    @RequiresPermissions("schedule:view")
    @PermissionAnnotation("任务页面")
    @RequestMapping("view")
    public String view() {
        return "schedule/schedule";
    }


    @RequiresPermissions("schedule:list")
    @PermissionAnnotation("任务列表")
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ScheduleQueryObject qo) throws Exception {
        scheduleService.checkState();//检查是否有未完成任务
        return scheduleService.query(qo);
    }


    @RequiresPermissions("schedule:saveOrUpdate")
    @PermissionAnnotation("任务保存")
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Task record) throws Exception {
        if (record.getId() == null) {
            scheduleService.insert(record);
        } else {
            scheduleService.updateByPrimaryKey(record);
        }
        return new JsonResult(true, "保存成功!");
    }


    @RequiresPermissions("schedule:deleteByPrimaryKey")
    @PermissionAnnotation("任务删除")
    @RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public JsonResult deleteByPrimaryKey(Long id) {
        try {
            scheduleService.deleteByPrimaryKey(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("删除失败,请重新操作");
        }
    }


    @RequiresPermissions("schedule:changeState")
    @PermissionAnnotation("任务标记完成")
    @ResponseBody
    @RequestMapping("changeState")
    public JsonResult changeState(Long id) throws Exception {
        try {
            scheduleService.changeState(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("标记失败!");
        }
    }

}
