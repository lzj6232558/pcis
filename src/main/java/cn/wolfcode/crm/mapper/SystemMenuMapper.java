package cn.wolfcode.crm.mapper;


import cn.wolfcode.crm.domain.SystemMenu;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);

    //查询的条件
    int queryForCount(QueryObject qo);
    List<SystemMenu> queryForList(QueryObject qo);

    void deleteChildByParentId(Long parentId);

    /**
     * 根据角色的id查询系统菜单
     * @param roleId
     * @return
     */
    List<SystemMenu> selectSystemMenuByRoleId(Long roleId);

    /**
     * 根据父菜单的sn去查询子菜单的信息
     * @param parentSn
     * @return
     */
    List<Map<String,Object>> selectMenuByParentId(String parentSn);

    /**
     * 查询数据库中的父菜单
     * @return
     */
    List<Map<String,Object>> selectParentMenu();

    List<Map<String, Object>> selectChildByParentId(Long parentId);

    List<SystemMenu> getMenu();

    List<SystemMenu> getChildrenMenu(Long parent_id);
}

