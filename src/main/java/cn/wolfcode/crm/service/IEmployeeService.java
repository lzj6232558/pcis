package cn.wolfcode.crm.service;


import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * created by king on 2017/11/27
 */
public interface IEmployeeService {
    void deleteByPrimaryKey(Long id);

    void insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    void updateByPrimaryKey(Employee record);
    //分页的条件
    PageResult query(QueryObject qo);


    void checkLogin(String name, String password);

    /**
     * 改变员工的状态
     * @param id
     */
    void changeState(Long id);

    void exporXls(HttpServletResponse response) throws Exception;

    void importXls(MultipartFile file) throws Exception;

    void dowloadXls(HttpServletResponse response) throws Exception;

    /**
     * 根据用户的用户名去查询这个用户的所有的信息
     * @param principal
     * @return
     */
    Employee selectByUsername(String principal);
}
