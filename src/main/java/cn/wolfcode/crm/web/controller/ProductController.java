package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.domain.Enquiryitem;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.domain.Safetymechanism;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ProductQueryObject;
import cn.wolfcode.crm.service.IProductService;
import cn.wolfcode.crm.service.ISafetymechanismService;
import cn.wolfcode.crm.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService service;
    @Autowired
    private ISafetymechanismService smService;

    @RequiresPermissions("product:view")
    @PermissionAnnotation("产品视图")
    @RequestMapping("view")
    public String view(Model model) throws Exception {
        //所有机构
        List<Safetymechanism> sms = smService.selectAll();
        model.addAttribute("sms",sms);
        return "product/view";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageResult query(ProductQueryObject qo) throws Exception {
        return service.query(qo);
    }

    @RequestMapping("queryItem")
    @ResponseBody
    public List<Product> queryItem(Long id) throws Exception {
        return service.selectAll(id);
    }
    @RequiresPermissions("product:saveOrUpdate")
    @PermissionAnnotation("产品保存/修改")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Product product) {
        Long id = product.getId();
        try {
            if (id == null) {
                service.insert(product);
            } else {
                service.updateByPrimaryKey(product);
            }
            return new JsonResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败!");
        }

    }

    //根据机构的id和合作的状态查询这个产品
    @RequestMapping("selectProductByItemId")
    @ResponseBody
    public List<Product> selectProductByItemId(Long id,Long enquiryId) {
        return service.selectProductByItemId(id,enquiryId);
    }
    @RequestMapping("selectProductByItemId2")
    @ResponseBody
    public List<Product> selectProductByItemId2(Long id) {
        return service.selectProductByItemId2(id);
    }

    @RequestMapping("updateTotalMoney")
    @ResponseBody
    public JsonResult updateTotalMoney(Product product,Long[] productIds,Long enquiryId) {
        try {
            service.updateTotalMoney(product,productIds,enquiryId);
            return new JsonResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败!");
        }

    }

    @RequestMapping("list")
    @ResponseBody
    public Object list() throws Exception {
        return service.list();
    }
    @RequestMapping("updateTotalMoney2")
    @ResponseBody
    public JsonResult updateTotalMoney2(Product product) {
        try {
            service.updateTotalMoney2(product);
            return new JsonResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败!");
        }
    }

    @RequestMapping("selectTotalMoney")
    @ResponseBody
    public List<Enquiryitem> selectTotalMoney(Long enquiryId) {
        return service.selectTotalMoney(enquiryId);
    }

}
