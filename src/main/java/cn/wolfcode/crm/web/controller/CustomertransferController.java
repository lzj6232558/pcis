package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Customertransfer;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.service.ICustomertransferService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("customertransfer")
public class CustomertransferController {
    @Autowired
    private ICustomertransferService customertransferService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    //
    //视图:
    @RequestMapping("view")
    public String view(){
        return "/customertransfer/view";
    }

    // 查询开发失败记录表
    @RequestMapping("query")
    @ResponseBody  // springmvc对 json的支持
    public Object query(QueryObject qo){
        PageResult query = customertransferService.query(qo);
        return customertransferService.query(qo);
    }

    @RequestMapping("save")
    @ResponseBody

    public JsonResult save(Customertransfer entity){   // 保存 现在 需要    旧市场专员   新市场专员  客户  以及原因
        try {
            Long id = entity.getCustomer().getId();              // 员工
            Long changeId = entity.getNewseller().getId();      //  新负责人
            customerService.updateChange(changeId,id);
            customertransferService.insert(entity);
            // 并且改变负责人:
           /*Customer customer = customerService.selectByPrimaryKey(id);
            customerService.updateChangeuser(customer);*/
            return new JsonResult(true,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败");
        }
    }

}
