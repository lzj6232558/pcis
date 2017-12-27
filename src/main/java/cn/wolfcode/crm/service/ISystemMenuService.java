package cn.wolfcode.crm.service;


import cn.wolfcode.crm.domain.SystemMenu;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/11/27
 */
public interface ISystemMenuService {
    void deleteByPrimaryKey(Long id);

    void insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    void updateByPrimaryKey(SystemMenu record);
    //分页的条件
    PageResult query(QueryObject qo);

    void deleteChildByParentId(Long parentId);

    /**
     * 根据父菜单的sn去查询子菜单的id,pid,name.controller也就是url
     * @return
     * @param parentSn
     */
    List<Map<String,Object>> selectMenuByParentId(String parentSn);

    /**
     * 查询数据库中所有的父菜单
     * @return
     */
    List<Map<String,Object>> selectParentMenu();

    List<Map<String,Object>> selectChildByParentId(Long parentId);

    /**
     * 查询父菜单以及下面的子菜单
     * @return
     */
    List<SystemMenu> getMenu();

    /**
     * 获取子菜单
     * @param parent_id 父菜单id
     * @return
     */
    List<SystemMenu> getChildrenMenu(Long parent_id);
}
