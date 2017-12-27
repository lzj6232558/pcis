package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {
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
    int insert(Department record);

    /**
     * 查询
     * @param id
     * @return
     */
    Department selectByPrimaryKey(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Department> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Department record);

    /**
     * 停用部门
     * @param id
     */
    void changeState(Long id);

    List<Map<String,Object>> findIdAndName();

    /**
     * 根据部门名称查询部门对象
     * @return
     */
    Department getDepartmentByDept(String deptName);
}