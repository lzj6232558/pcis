package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("systemLog")
public class SystemLogController {
    @Autowired
    private ISystemLogService systemLogService;


    //视图:
    @RequestMapping("view")
    public String view(){
        return "/systemLog/view";
    }

    //查询数据:
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(QueryObject qo){
        return systemLogService.query(qo);
    }




}
