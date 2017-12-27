package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    void deleteByPrimaryKey(Long id);

    void insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    void updateByPrimaryKey(Employee record);

    int queryForCount(QueryObject qo);

    List<Employee> queryForList(QueryObject qo);

    Employee checkLogin(@Param("name") String name, @Param("password") String password);

    void changeState(Long id);

    void deleteEmpRoleRelation(Long id);

    void insertEmpRoleRelation(@Param("EmpId") Long id, @Param("roleId") Long id1);

    Employee selectByUsername(String principal);


    /**
     * 根据员工名查询员工对象:(不是真实姓名)
     * @param username
     * @return
     */
    Employee getEmployeeByUsername(String username);
}