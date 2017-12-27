package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21/021.
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

///

    @RequestMapping("view")
    public String view(){
        return "customer/view";
    }


    @RequestMapping("list")
    @ResponseBody

    public List<Customer> list(){
        List<Customer> customerList = customerService.selectAll();
        return customerList;
    }


    // 潜在客户查询
    @RequestMapping("query")
    @ResponseBody  // springmvc对 json的支持
    public Object query(CustomerQueryObject qo){
        qo.setStatus(1);
        return customerService.query(qo);
    }

    //添加或修改数据:
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    @RequiresPermissions("customer:saveOrUpdate")
    @PermissionAnnotation("客户新增/编辑")
    public JsonResult saveOrUpdate(Customer entity) {
        try {
            if (entity.getId() == null) {
                customerService.insert(entity);
                return new JsonResult(true, "保存成功!");
            } else {
                customerService.updateByPrimaryKey(entity);
                return new JsonResult(true, "更新成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败!");
        }
    }

    // 试验: 正式用户修改及资源池客户修改
    @ResponseBody
    @RequestMapping("update")
    public JsonResult update(Customer entity) {
        try {
                customerService.updateFormal(entity);
                return new JsonResult(true, "更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败!");
        }
    }




    //潜在客户转为正式客户
    @RequestMapping("formal")
    @ResponseBody
    public JsonResult formal(Long id) {
        try {
            if(id!=null){
                customerService.updateStatus(2,id);
            }
            return new JsonResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "修改失败!");
        }
    }

    //潜在客户开发失败
    @RequestMapping("failure")
    @ResponseBody
    public JsonResult failure(Long id) {
        try {
            if(id!=null){
                customerService.updateStatus(-1,id);
            }
            return new JsonResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "修改失败!");
        }
    }


    //客户流失
    @RequestMapping("away")
    @ResponseBody
    public JsonResult away(Long id) {
        try {
            if(id!=null){
                customerService.updateStatus(-2,id);}
            return new JsonResult(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "修改失败!");
        }
    }

    // 资源池客户转化为潜在客户
    @RequestMapping("pool")
    @ResponseBody
    public JsonResult pool(Long id) {
        try {
            if(id!=null){
                customerService.updateStatus(1,id);}
            return new JsonResult(true, "开发成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "开发失败!");
        }
    }

    // 客户移入资源池
    @RequestMapping("move")
    @ResponseBody
    public JsonResult move(Long id) {
        try {
            if(id!=null){
                customerService.updateStatus(0,id);}
            return new JsonResult(true, "开发成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "开发失败!");
        }
    }
    @RequestMapping("selectOfficialCustomer")
    @ResponseBody
    public List<Customer> selectOfficialCustomer(){
        return customerService.selectOfficialCustomer();
    }
}
