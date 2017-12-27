package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Customerlost;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface CustomerlostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customerlost record);

    Customerlost selectByPrimaryKey(Long id);

    List<Customerlost> selectAll();

    int updateByPrimaryKey(Customerlost record);

    int queryForCount(QueryObject qo);

    List<Customerlost> queryForList(QueryObject qo);
}