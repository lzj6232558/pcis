package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.anno.PermissionAnnotation;
import cn.wolfcode.crm.query.ClaimChartQueryObject;
import cn.wolfcode.crm.query.SaleChartQueryObject;
import cn.wolfcode.crm.service.IChartService;
import com.alibaba.druid.support.json.JSONUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring mvc 中的modelandview对象由InternalResourceViewResolver解析器解析后，
 * 将modelandview中的参数和指定的页面组合在一起，
 * 当成一个完整的html文档返回到请求端，这种方式同时完成了参数的传递和页面的跳转，
 * 其缺点在于比起普通的请求来说，这种方式传输的数据要多得多，
 * 大量使用会使页面加载变得很慢，影响体验，在关键点使用即可。
 */
@Controller
@RequestMapping("chart")
public class ChartController {
    @Autowired
    private IChartService chartService;
    @PermissionAnnotation("销售报表视图")
    @RequiresPermissions("chart:sale")
    @RequestMapping("sale")
    public String sale() {
        return "chart/saleChart";
    }
    @PermissionAnnotation("理赔报表视图")
    @RequiresPermissions("chart:claim")
    @RequestMapping("claim")
    public String claim() {
        return "chart/claimChart";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Map<String, Object>> list(SaleChartQueryObject qo) {
        return chartService.saleChart(qo);
    }
    @RequestMapping("claimList")
    @ResponseBody
    public List<Map<String, Object>> claimList(ClaimChartQueryObject qo) {
        return chartService.claimChart(qo);
    }

    //销售柱状图
    @PermissionAnnotation("销售报表柱状图")
    @RequiresPermissions("chart:saleBar")
    @RequestMapping("saleBar")
    @ResponseBody
    public ModelAndView saleBar(SaleChartQueryObject qo) {
        ModelAndView mav=new ModelAndView("chart/saleBar");

        //存放分组的属性(横坐标)
        List<Object> groupTypeList = new ArrayList<>();
        //所有总额
        List<Object> amountList = new ArrayList<>();
        //分组类型对应的总额
        List<Map<String, Object>> maps = chartService.saleChart(qo);
        System.out.println(maps);
        for (Map<String, Object> map : maps) {
            groupTypeList.add(map.get("groupType"));
            amountList.add(map.get("totalAmount"));

        }
        //设置分组类型名字
        if ("e.username".equals(qo.getGroupType())) {
            mav.addObject("groupType", "销售人员");
        } else if ("safe.name".equals(qo.getGroupType())) {
            mav.addObject("groupType", "保险机构");
        } else if ("p.name".equals(qo.getGroupType())) {
            mav.addObject("groupType", "保险产品");
        } else if ("date_format(po.beginDate,'%Y-%m')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "销售日期(月)");
        } else if ("date_format(po.beginDate,'%Y-%m-%d')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "销售日期(日)");
        }
        //共享到页面 通过${name}取值
        mav.addObject("amountList", JSONUtils.toJSONString(amountList));
        mav.addObject("groupTypeList", JSONUtils.toJSONString(groupTypeList));
        return mav;
    }

    //销售饼状图
    @PermissionAnnotation("销售报表饼状图")
    @RequiresPermissions("chart:salePie")
    @RequestMapping("salePie")
    @ResponseBody
    public ModelAndView salePie(SaleChartQueryObject qo) {
        ModelAndView mav=new ModelAndView("/chart/salePie");
        //存放分组类型对应的总额
        List<Map<String, Object>> groupList = new ArrayList();
        BigDecimal max = BigDecimal.ZERO;
        List<Map<String, Object>> maps = chartService.saleChart(qo);
        for (Map<String, Object> item : maps) {
            //页面数据是一个map<name,(groupType)>
            Map<String, Object> map = new HashMap();
            //访问来源
            map.put("name", item.get("groupType"));
            map.put("value", item.get("totalAmount"));
            //totalAmount大于max
            if (max.compareTo((BigDecimal) item.get("totalAmount")) < 0) {
                max = (BigDecimal) item.get("totalAmount");
            }
            groupList.add(map);
        }
        if ("e.username".equals(qo.getGroupType())) {
            mav.addObject("groupType", "销售人员");
        } else if ("safe.name".equals(qo.getGroupType())) {
            mav.addObject("groupType", "保险机构");
        } else if ("p.name".equals(qo.getGroupType())) {
            mav.addObject("groupType", "保险产品");
        } else if ("date_format(po.beginDate,'%Y-%m')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "销售日期(月)");
        } else if ("date_format(po.beginDate,'%Y-%m-%d')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "销售日期(日)");
        }
        //转为json字符串共享到页面
        mav.addObject("groupList", JSONUtils.toJSONString(groupList));
        mav.addObject("max", max);
        return mav;
    }
    //理赔柱状图
    @PermissionAnnotation("理赔报表柱状图")
    @RequiresPermissions("chart:claimBar")
    @RequestMapping("claimBar")
    @ResponseBody
    public ModelAndView claimBar(ClaimChartQueryObject qo) {
        ModelAndView mav=new ModelAndView("chart/claimBar");

        //存放分组的属性(横坐标)
        List<Object> groupTypeList = new ArrayList<>();
        //所有总额
        List<Object> amountList = new ArrayList<>();
        //分组类型对应的总额
        List<Map<String, Object>> maps = chartService.claimChart(qo);
        System.out.println(maps);
        for (Map<String, Object> map : maps) {
            groupTypeList.add(map.get("groupType"));
            amountList.add(map.get("totalAmount"));

        }
        //设置分组类型名字
        if ("e.username".equals(qo.getGroupType())) {
            mav.addObject("groupType", "业务人员");
        } else if ("date_format(a.handDate,'%Y-%m')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "理赔日期(月)");
        } else if ("date_format(a.handDate,'%Y-%m-%d')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "理赔日期(日)");
        }
        //共享到页面 通过${name}取值
        mav.addObject("amountList", JSONUtils.toJSONString(amountList));
        mav.addObject("groupTypeList", JSONUtils.toJSONString(groupTypeList));
        return mav;
    }

    //理赔饼状图
    @PermissionAnnotation("理赔报表饼状图")
    @RequiresPermissions("chart:claimPie")
    @RequestMapping("claimPie")
    @ResponseBody
    public ModelAndView claimPie(ClaimChartQueryObject qo) {
        ModelAndView mav=new ModelAndView("/chart/claimPie");
        //存放分组类型对应的总额
        List<Map<String, Object>> groupList = new ArrayList();
        BigDecimal max = BigDecimal.ZERO;
        List<Map<String, Object>> maps = chartService.claimChart(qo);
        for (Map<String, Object> item : maps) {
            //页面数据是一个map<name,(groupType)>
            Map<String, Object> map = new HashMap();
            //访问来源
            map.put("name", item.get("groupType"));
            map.put("value", item.get("totalAmount"));
            //totalAmount大于max
            if (max.compareTo((BigDecimal) item.get("totalAmount")) < 0) {
                //漏斗图的最大值,最大值是总额
                max = (BigDecimal) item.get("totalAmount");
            }
            groupList.add(map);
        }
        if ("e.username".equals(qo.getGroupType())) {
            mav.addObject("groupType", "业务人员");
        }else if ("date_format(a.handDate,'%Y-%m')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "理赔日期(月)");
        } else if ("date_format(a.handDate,'%Y-%m-%d')".equals(qo.getGroupType())) {
            mav.addObject("groupType", "理赔日期(日)");
        }
        //转为json字符串共享到页面
        mav.addObject("groupList", JSONUtils.toJSONString(groupList));
        mav.addObject("max", max);
        return mav;
    }

}
