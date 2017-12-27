package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * created by king on 2017/12/17
 */
public interface ISystemLogService {
    void deleteByPrimaryKey(Long id);

    void insert(SystemLog record);

    public void updateByPrimaryKey(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();


    //分页的条件
    PageResult query(QueryObject qo);
}
