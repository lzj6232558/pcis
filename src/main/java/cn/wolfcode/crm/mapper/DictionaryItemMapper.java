package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.query.CustomerQueryObject;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictionaryItem record);

    DictionaryItem selectByPrimaryKey(Long id);

    List<DictionaryItem> selectAll();

    int updateByPrimaryKey(DictionaryItem record);
    int queryForCount(QueryObject qo);

    List<DictionaryItem> queryForList(QueryObject qo);

    List<DictionaryItem> select(String sn);
}