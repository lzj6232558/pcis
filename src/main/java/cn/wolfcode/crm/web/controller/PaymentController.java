package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.PolicyQueryObject;
import cn.wolfcode.crm.service.IPaymentService;
import cn.wolfcode.crm.service.IPolicyService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private IPolicyService policyService;
    @Autowired
    private IPaymentService paymentService;


    @RequiresPermissions("payment:view")
    @PermissionAnnotation("付款界面")
    @RequestMapping("view")
    public String view() {
        return "/payment/payment";
    }

    @RequiresPermissions("payment:query")
    @PermissionAnnotation("付款列表")
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(PolicyQueryObject qo) {
        qo.setState(2);//查询状态为2待缴费的状态
        return policyService.query(qo);
    }


    /**
     * 通过保单号获取订购的产品
     * @param policyId
     * @return
     */
    @ResponseBody
    @RequestMapping("getProducts")
    public List<Product> getProducts(Long policyId) {
        return paymentService.getProducts(policyId);
    }


    @RequiresPermissions("payment:save")
    @PermissionAnnotation("保单缴费")
    @ResponseBody
    @RequestMapping("save")
    public JsonResult save(Long  id, Integer paymentWay) {
        try {
            paymentService.pay(id, paymentWay);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("保存失败");
        }
    }

    @RequiresPermissions("payment:returnBack")
    @PermissionAnnotation("保单撤回")
    @ResponseBody
    @RequestMapping("returnBack")
    public JsonResult returnBack(Long id) {
        try {
            paymentService.changeState(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("保存失败");
        }
    }

 /*   //查询暂存单,也就是状态为0
    @RequestMapping("temporaryBill")
    public String selectTemporary() {
        return "/policy/temporary";
    }

    //查询待审核保单  状态为1
    @RequestMapping("checkPending")
    public String checkPending() {

        return "/policy/checkPending";
    }
    //查询批改单
    @RequestMapping("modify")
    public String modify() {

        return "/policy/modify";
    }
    //保单的历史记录
    @RequestMapping("policyHistory")
    public String policyHistory() {

        return "/policy/policyHistory";
    }
    //更改保单的状态
    @RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Policy policy) {
        try {
            policyService.changeState(policy);
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"操作失败");
        }

    }

    //审核保单
    @RequestMapping("audit")
    @ResponseBody
    public JsonResult audit(Policy policy) {
        try {
            policyService.audit(policy);
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"操作失败");
        }
    }*/
}
