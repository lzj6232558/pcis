package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Plan;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.PlanQueryObject;
import cn.wolfcode.crm.service.IPlanService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("plan")
public class PlanController {
    @Autowired
    private IPlanService service;



    @RequestMapping("result")
    @ResponseBody
    public JsonResult result(Integer result,Long id){
        try{
            service.changeResultById(result,id);
            return new JsonResult();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new JsonResult(false,"操作失败");
    }

    @RequestMapping("view")
    public String view() throws Exception {
        return "plan/view";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageResult query(PlanQueryObject qo) throws Exception {
        System.out.println(service.query(qo));
        return service.query(qo);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Plan plan) {
        Long id = plan.getId();
        try {
            if (id == null) {
                service.insert(plan);
            } else {
                service.updateByPrimaryKey(plan);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "删除失败!");
    }

}
