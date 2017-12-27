package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Customerlost;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerlostService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("customerlost")

// 开发流失记录
public class CustomerlostController {
    @Autowired
    private ICustomerlostService customerlostService;

    //视图:
    @RequestMapping("view")
    public String view(){
        return "/customerlost/view";
    }

    // 查询开发失败记录表
    @RequestMapping("query")
    @ResponseBody  // springmvc对 json的支持
    public Object query(QueryObject qo){
        PageResult query = customerlostService.query(qo);
        return customerlostService.query(qo);
    }



    //仅做客户流失
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Customerlost entity){
        System.out.println(entity);
        customerlostService.insert(entity);
        return new JsonResult(true,"保存成功!");
    }
}
