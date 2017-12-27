package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Enquiry;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EnquiryQueryObject;
import cn.wolfcode.crm.service.IEnquiryService;
import cn.wolfcode.crm.utils.EncodingUtils;
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
@RequestMapping("enquiry")
public class EnquiryController {
    @Autowired
    private IEnquiryService enquiryService;


    //视图:
    @RequiresPermissions("enquiry:view")
    @PermissionAnnotation("询价单视图")
    @RequestMapping("view")
    public String view() {
        return "/enquiry/view";
    }

    //视图:
    @RequiresPermissions("enquiry:enquiryDetail")
    @PermissionAnnotation("询价单明细视图")
    @RequestMapping("enquiryDetail")
    public String enquiryDetail() {
        return "/enquiry/enquiryDetail";
    }

    //查询询价单的明细
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(EnquiryQueryObject qo) {
        return enquiryService.query(qo);
    }

    //添加或修改数据:

    @RequiresPermissions("enquiry:saveOrUpdate")
    @PermissionAnnotation("询价单保存/修改")
    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public JsonResult saveOrUpdate(Enquiry entity, Long[] productIds, BigDecimal[] totalMoney, BigDecimal[] annuafee) {
        try {
            if (entity.getId() == null) {
                entity.setOrigin(EncodingUtils.encoding(entity.getOrigin()));
                entity.getCar().setBrand(EncodingUtils.encoding(entity.getCar().getBrand()));
                entity.getCar().setPlateNumber(EncodingUtils.encoding(entity.getCar().getPlateNumber()));
                entity.getCustomer().setName(EncodingUtils.encoding(entity.getCustomer().getName()));
                enquiryService.insert(entity, productIds, totalMoney, annuafee);
                return new JsonResult(true, "保存成功!");
            } else {
                enquiryService.updateByPrimaryKey(entity, productIds, totalMoney, annuafee);
                return new JsonResult(true, "更新成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败!");
        }
    }

    //转存保单
    @ResponseBody
    @RequestMapping("saveBill")
    public JsonResult saveBill(Long id) {
        try {
            enquiryService.saveBill(id);
            return new JsonResult(true, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败!");
        }
    }
}
