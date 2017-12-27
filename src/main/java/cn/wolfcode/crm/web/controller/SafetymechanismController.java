package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Safetymechanism;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.SafeQueryObject;
import cn.wolfcode.crm.service.ISafetymechanismService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("safe")
public class SafetymechanismController {
    @Autowired
    private ISafetymechanismService service;

    @RequestMapping("view")
    public String view() throws Exception {
        return "safetymechanism/view";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageResult query(SafeQueryObject qo) throws Exception {
        return service.query(qo);
    }
    @RequestMapping("list")
    @ResponseBody
    public List<Safetymechanism> list() throws Exception {
        return service.selectAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Safetymechanism safetymechanism) {
        Long id = safetymechanism.getId();
        try {
            if (id == null) {
                service.insert(safetymechanism);
            } else {
                service.updateByPrimaryKey(safetymechanism);
            }
            return new JsonResult(true,"保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败!");


    }
}
