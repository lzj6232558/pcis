package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.ChartsMapper;
import cn.wolfcode.crm.query.ClaimChartQueryObject;
import cn.wolfcode.crm.query.SaleChartQueryObject;
import cn.wolfcode.crm.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ChartServiceImpl implements IChartService {
    @Autowired
    private ChartsMapper chartsMapper;

    @Override
    public List<Map<String, Object>> saleChart(SaleChartQueryObject qo) {
        return chartsMapper.saleChart(qo);
    }

    @Override
    public List<Map<String, Object>> claimChart(ClaimChartQueryObject qo) {
        return chartsMapper.claimChart(qo);
    }
}
