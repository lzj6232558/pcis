package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Policy;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.PolicyQueryObject;
import cn.wolfcode.crm.service.IPolicyService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * Created by mm on 2017/12/21.
 */
@Controller
@RequestMapping("policy")
public class PolicyController {
    @Autowired
    private IPolicyService policyService;

    @RequiresPermissions("policy:view")
    @PermissionAnnotation("保单视图")
    //投保单视图
    @RequestMapping("view")
    public String view() {
        return "/policy/view";
    }

    //查询数据:
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(PolicyQueryObject qo) {
        return policyService.query(qo);
    }


    //添加或修改数据:
    @RequiresPermissions("policy:saveOrUpdate")
    @PermissionAnnotation("保单保存和修改")
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Policy entity, Long[] pIds, Long totalAmount, BigDecimal[] totalMoney,BigDecimal[] annuaFlee) {
        try {
            if (entity.getId() == null) {
                policyService.insert(entity, pIds, totalAmount,totalMoney,annuaFlee);
            } else {
                policyService.updateByPrimaryKey(entity, pIds, totalAmount,totalMoney,annuaFlee);
            }
            return new JsonResult(true, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败");
        }
    }

    @RequiresPermissions("policy:temporaryBill")
    @PermissionAnnotation("保单暂存单视图")
    //查询暂存单,也就是状态为0
    @RequestMapping("temporaryBill")
    public String selectTemporary() {
        return "/policy/temporary";
    }



    @RequiresPermissions("policy:checkPending")
    @PermissionAnnotation("保单待审核视图")
    //查询待审核保单  状态为1
    @RequestMapping("checkPending")
    public String checkPending() {

        return "/policy/checkPending";
    }
    //查询批改单
    @RequiresPermissions("policy:modify")
    @PermissionAnnotation("保单批改单视图")
    @RequestMapping("modify")
    public String modify() {

        return "/policy/modify";
    }

    @RequiresPermissions("policy:policyHistory")
    @PermissionAnnotation("保单历史记录视图")
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
    @ResponseBody
    @RequestMapping("audit")
    public JsonResult audit(Policy policy) {
        try {
            policyService.audit(policy);
            return new JsonResult(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"操作失败");
        }
    }

    //根据保单编号查询保单状态
    @ResponseBody
    @RequestMapping("getStateBySn")
    public Policy getStateBySn(String sn) {
        return policyService.getStateBySn(sn);
    }
}
