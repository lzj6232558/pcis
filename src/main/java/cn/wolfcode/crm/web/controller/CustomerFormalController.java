package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/22/022.
 */
// 正式客户
@Controller
@RequestMapping("customerFormal")
public class CustomerFormalController {

    @Autowired
    ICustomerService customerService;
    // 视图跳转


    @RequestMapping("view")
    public String view(){
        return "customer/viewFormal";
    }


    // 正式客户查询
    @RequestMapping("query")
    @ResponseBody  // springmvc对 json的支持
    public Object queryFormal(CustomerQueryObject qo){
        qo.setStatus(2);
        PageResult query = customerService.query(qo);
        return customerService.query(qo);
    }
}
