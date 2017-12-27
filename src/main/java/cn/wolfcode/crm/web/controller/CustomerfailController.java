package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.Customerfail;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.service.ICustomerfailService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("customerfail")

// 开发失败记录
public class CustomerfailController {
    @Autowired
    private ICustomerfailService customerfailService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;

    //视图:
    @RequestMapping("view")
    public String view(){
        return "/customerfail/view";
    }

    @RequestMapping("query")
    @ResponseBody  // springmvc对 json的支持
    public Object query(QueryObject qo){
        PageResult query = customerfailService.query(qo);
        return customerfailService.query(qo);
    }


    //查询正式客户
    @RequestMapping("selectOfficialCustomer")
    @ResponseBody
    public java.util.List<Customer> selectOfficialCustomer(){

        return customerService.selectOfficialCustomer();
    }


    //仅做客户开发失败
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    @RequiresPermissions("customerfail:saveOrUpdate")
    @PermissionAnnotation("开发失败")
    public JsonResult saveOrUpdate(Customerfail entity){
        System.out.println(entity);
        customerfailService.insert(entity);
        return new JsonResult(true,"保存成功!");
    }
}
