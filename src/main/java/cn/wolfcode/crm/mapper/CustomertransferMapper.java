package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Customertransfer;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
//
public interface CustomertransferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customertransfer record);

    Customertransfer selectByPrimaryKey(Long id);

    List<Customertransfer> selectAll();

    int updateByPrimaryKey(Customertransfer record);

    int queryForCount(QueryObject qo);

    List<Customertransfer> queryForList(QueryObject qo);
}