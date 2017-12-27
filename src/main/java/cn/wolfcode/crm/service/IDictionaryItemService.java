package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * created by king on 2017/12/17
 */
public interface IDictionaryItemService {
    void deleteByPrimaryKey(Long id);

    void insert(DictionaryItem record);

    DictionaryItem selectByPrimaryKey(Long id);

    List<DictionaryItem> selectAll();

    void updateByPrimaryKey(DictionaryItem record);
    //分页的条件
    PageResult query(QueryObject qo);

    List<DictionaryItem> select(String sn);
}
