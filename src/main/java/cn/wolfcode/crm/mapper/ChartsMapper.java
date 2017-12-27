package cn.wolfcode.crm.mapper;


import cn.wolfcode.crm.query.ClaimChartQueryObject;
import cn.wolfcode.crm.query.SaleChartQueryObject;

import java.util.List;
import java.util.Map;

public interface ChartsMapper {
/**
 * 订单报表,将每条数据封装到一个Map集合中
 * 返回多个Map集合
 */

    //销售报表
    List<Map<String,Object>> saleChart(SaleChartQueryObject qo);

    List<Map<String,Object>> claimChart(ClaimChartQueryObject qo);

}
