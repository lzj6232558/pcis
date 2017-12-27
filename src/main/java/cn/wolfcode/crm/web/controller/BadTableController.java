package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.BadTable;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.BadTableQueryObject;
import cn.wolfcode.crm.service.IBadTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("badTable")
public class BadTableController {
    @Autowired
    private IBadTableService service;

    @RequestMapping("view")
    public String view(Model model) throws Exception {
        return "accident/badTable";
    }

    @ResponseBody
    @RequestMapping("query")
    public PageResult query(BadTableQueryObject qo) throws Exception {
        return service.query(qo);
    }

    //根据事故明细表查询赔偿单:
    @ResponseBody
    @RequestMapping("getBadListById")
    public List<BadTable> getBadListById(Long id) throws Exception {
        return service.getBadListById(id);
    }
}
