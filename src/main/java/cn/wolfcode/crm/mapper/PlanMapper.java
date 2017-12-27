package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Plan;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlanMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Plan record);

    Plan selectByPrimaryKey(Long id);

    List<Plan> selectAll();

    void updateByPrimaryKey(Plan record);
    int queryCount(QueryObject qo);

    List<Plan> queryList(QueryObject qo);
    //更改计划结果
    void changeResultById(@Param("result") Integer result,@Param("id") Long id);
}