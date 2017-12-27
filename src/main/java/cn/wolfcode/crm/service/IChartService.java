package cn.wolfcode.crm.service;


import cn.wolfcode.crm.query.ClaimChartQueryObject;
import cn.wolfcode.crm.query.SaleChartQueryObject;

import java.util.List;
import java.util.Map;


public interface IChartService {
    List<Map<String,Object>> saleChart(SaleChartQueryObject qo);


    List<Map<String,Object>> claimChart(ClaimChartQueryObject qo);
}
