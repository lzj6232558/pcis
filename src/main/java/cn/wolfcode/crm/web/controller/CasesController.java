package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Cases;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICasesService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("cases")
public class CasesController {
    @Autowired
    private ICasesService casesService;


    //视图:
    @RequestMapping("view")
    public String view(){
        return "/cases/view";
    }

    //查询数据:
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(QueryObject qo){
        return casesService.query(qo);
    }

    //添加或修改数据:
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Cases entity){
        if(entity.getId() == null){
            casesService.insert(entity);
        }else{
            casesService.updateByPrimaryKey(entity);
        }
        return new JsonResult(true,"保存成功!");
    }


}
