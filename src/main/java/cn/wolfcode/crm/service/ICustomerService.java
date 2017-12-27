package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21/021.
 */

public interface ICustomerService {

    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    Customer selectByPrimaryKey(Long id);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    PageResult query(QueryObject qo);

    void updateChangeuser(Customer customer);

    void updateStatus(Integer status,Long id);

    //
    void updateFormal(Customer record);
    // 移交的时候改变负责人
    void updateChange( Long changeId,Long id);

    /**
     * 查询会员客户
     * @return
     */
    List<Customer> selectOfficialCustomer();
}
