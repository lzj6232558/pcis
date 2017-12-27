package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/22/022.
 */
@Controller
@RequestMapping("customerpool")
public class CustomerPool {

    @Autowired
    ICustomerService customerService;

    @RequestMapping("view")
    public String view(){
        return "customer/viewpol";
    }

    // 资源池客户查询
    @RequestMapping("query")
    @ResponseBody  // springmvc对 json的支持
    public Object queryFormal(CustomerQueryObject qo){
        qo.setStatus(0);
        return customerService.query(qo);
    }
}
