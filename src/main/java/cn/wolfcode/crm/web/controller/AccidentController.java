package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Accident;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AccidentQueryObject;
import cn.wolfcode.crm.service.IAccidentService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("accident")
public class AccidentController {
    @Autowired
    private IAccidentService service;

    @RequestMapping("view")
    public String view(Model model) throws Exception {
        return "accident/accident";
    }

    @ResponseBody
    @RequestMapping("query")
    public PageResult query(AccidentQueryObject qo) throws Exception {
        return service.query(qo);
    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Accident accident) {
        try {
            if (accident.getId() == null) {
                //设置报案单客服:
                Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
                accident.setEmployeeId(employee);
                service.insert(accident);
            } else {
                service.updateByPrimaryKey(accident);
            }
            return new JsonResult(true,"保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败!");
    }
    //删除
    @ResponseBody
    @RequestMapping("delete")
    public JsonResult delete(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult("删除失败!");
        }
    }

}
