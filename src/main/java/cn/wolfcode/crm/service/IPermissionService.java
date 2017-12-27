package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by mm on 2017/12/17.
 */
public interface IPermissionService {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Permission record);

    /**
     * 查询一条
     * @param id
     * @return
     */
    Permission selectByPrimaryKey(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Permission> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Permission record);

    /**
     * 高级查询:
     * @param qo
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 根据角色地查询权限
     * @param roleId
     * @return
     */
    List<Permission> getPermissionByRoleId(Long roleId);

    /**
     * 加载权限:
     */
    void reload();

    /**
     * 根据员工id查询权限:
     * @param id
     */
    List<String> selectByEmployeeId(Long id);

    /**
     * 根据员工id查询权限集合:
     * @param id
     * @return
     */
    List<String> selectPermissionByEmpId(Long id);

}
