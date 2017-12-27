package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Cases;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * created by king on 2017/12/17
 */
public interface ICasesService {
    void deleteByPrimaryKey(Long id);

    void insert(Cases record);

    Cases selectByPrimaryKey(Long id);

    List<Cases> selectAll();

    void updateByPrimaryKey(Cases record);
    //分页的条件
    PageResult query(QueryObject qo);

}
