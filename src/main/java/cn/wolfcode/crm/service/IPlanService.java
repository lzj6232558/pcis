package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Plan;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.PlanQueryObject;

import java.util.List;

public interface IPlanService {
    void deleteByPrimaryKey(Long id);

    void insert(Plan record);

    Plan selectByPrimaryKey(Long id);

    List<Plan> selectAll();

    void updateByPrimaryKey(Plan record);

    PageResult query(PlanQueryObject qo);
    void changeResultById(Integer result,Long id);

}
