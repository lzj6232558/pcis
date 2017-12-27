package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Plan;
import cn.wolfcode.crm.mapper.PlanMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.PlanQueryObject;
import cn.wolfcode.crm.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PlanServiceImpl implements IPlanService {
    @Autowired
    private PlanMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);

    }

    @Override
    public void insert(Plan record) {
        mapper.insert(record);

    }

    @Override
    public Plan selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Plan> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Plan record) {
        mapper.updateByPrimaryKey(record);

    }

    @Override
    public PageResult query(PlanQueryObject qo) {
        int count=mapper.queryCount(qo);
        if (count<0){
            return new PageResult(0, Collections.emptyList());
        }
        List<Plan> list=mapper.queryList(qo);
        return new PageResult(count,list);
    }

    @Override
    public void changeResultById(Integer result, Long id) {
        mapper.changeResultById(result,id);


    }
}
