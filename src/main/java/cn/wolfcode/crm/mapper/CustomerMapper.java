package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Long id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);


    int queryForCount(QueryObject qo);

    List<Customer> queryForList(QueryObject qo);

    void updateChangeuser(Long id);

    void updateStatus(@Param("status") Integer status, @Param("id")Long id, @Param("employee")Employee employee);

    // 试验:  修改正式客户
    void updateFormal(Customer record);

    /**
     * 查询正式客户
     * @return
     */
    List<Customer> selectOfficialCustomer();

    /**
     * 根据询价单的id,查询客户又的信息
     * @param enqId
     * @return
     */
    Customer selectCusByEnqId(Long enqId);

    Customer insuerCustomer(String idno);

    //  用于移交客户的时候修改负责人
    void updateChange(@Param("changeId") Long changeId,@Param("id") Long id);

    /**
     * 根据客户的id更改状态
     * @param id
     */
    void updateState2(Long id);
}