package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.FeePolicyQueryObject;
import cn.wolfcode.crm.query.PolicyQueryObject;
import cn.wolfcode.crm.service.IFeePolicyService;
import cn.wolfcode.crm.service.IPaymentService;
import cn.wolfcode.crm.service.IPolicyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("feePolicy")
public class FeePolicyController {
    @Autowired
    private IFeePolicyService feePolicyService;


    @RequiresPermissions("feePolicy:view")
    @PermissionAnnotation("收费页面")
    @RequestMapping("view")
    public String view() {
        return "/feePolicy/feePolicy";
    }

    @RequiresPermissions("feePolicy:query")
    @PermissionAnnotation("收费列表")
    @ResponseBody
    @RequestMapping("query")
    public PageResult query(FeePolicyQueryObject qo) {
        return feePolicyService.query(qo);
    }


    @ResponseBody
    @RequestMapping("autoSearchPolicySn")
    public List<Map<String, String>> autoSearchPolicySn(String param) {
        List<String> strings = feePolicyService.autoSearchPolicySn(param);
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("value", strings.get(i));
            map.put("text", strings.get(i));
            list.add(map);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("autoSearchKeywords")
    public List<Map<String, String>> autoSearchKeywords(String param) {
        List<String> strings = feePolicyService.autoSearchKeywords(param);
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("value", strings.get(i));
            map.put("text", strings.get(i));
            list.add(map);
        }
        return list;
    }


}
