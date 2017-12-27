package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Teambills;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface TeambillsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teambills record);

    Teambills selectByPrimaryKey(Long id);

    List<Teambills> selectAll();

    int updateByPrimaryKey(Teambills record);

    int queryForCount(QueryObject qo);

    List<Teambills> queryForList(QueryObject qo);

    List<Long>  selectCustomer();
}