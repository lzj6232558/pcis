package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Customerfail;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface CustomerfailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customerfail record);

    Customerfail selectByPrimaryKey(Long id);

    List<Customerfail> selectAll();

    int updateByPrimaryKey(Customerfail record);


    int queryForCount(QueryObject qo);

    List<Customerfail> queryForList(QueryObject qo);
}