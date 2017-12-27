package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Cases;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface CasesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cases record);

    Cases selectByPrimaryKey(Long id);

    List<Cases> selectAll();

    int updateByPrimaryKey(Cases record);

    int queryForCount(QueryObject qo);

    List<Cases> queryForList(QueryObject qo);
}