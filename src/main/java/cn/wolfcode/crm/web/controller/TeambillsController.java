package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Teambills;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.ITeambillsService;
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
@RequestMapping("teambills")
public class TeambillsController {
    @Autowired
    private ITeambillsService teambillsService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;

    //视图:
    @RequestMapping("view")
    public String view() {
        return "/teambills/view";
    }

    // 查询开发失败记录表
    @RequestMapping("query")
    @ResponseBody  // springmvc对 json的支持
    public Object query(QueryObject qo) {
        PageResult query = teambillsService.query(qo);
        return teambillsService.query(qo);
    }
//
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("teambills:saveOrUpdate")
    @PermissionAnnotation("团单新增/编辑")
    public JsonResult saveOrUpdate(Teambills teambill) {
        Long id = teambill.getId();
        Long customerId = teambill.getCustomer().getId();
        List<Long> longs = teambillsService.selectCustomer();
        try {
            for (Long aLong : longs) {
                if (longs.contains(customerId)) {
                    return new JsonResult(false, "同一用户不可重复添加");
                }
            }
            if (id == null) {
                teambillsService.insert(teambill);
            } else {
                teambillsService.updateByPrimaryKey(teambill);
            }
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败!");
    }
}
//
