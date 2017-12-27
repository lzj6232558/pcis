package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CustomerMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICustomerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21/021.
 */
@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Customer record) {
        // 设置录入时间和录入人以及当前状态
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        record.setInputuser(employee);
        record.setInputdate(new Date());
        //重新定义业务. 在新增时候就设置当前登录人为负责人.    只有对新增客户进行加入资源池或开发或移交时重新设置负责人
        record.setStatus(1);
        return customerMapper.insert(record);
    }

    @Override
    public Customer selectByPrimaryKey(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Customer> selectAll() {
        return customerMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Customer record) {
        // 注意未完成  要在这里加入开发的人..
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        record.setChangeuser(employee);
        record.setStatus(1);
        return customerMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = customerMapper.queryForCount(qo);
        if(count == 0){
            return PageResult.EMPTY_PAGE;
        }
        List<Customer> list = customerMapper.queryForList(qo);
        System.out.println(list);
        return new PageResult(count,list);
    }

    @Override
    public void updateChangeuser(Customer customer) {
        customerMapper.updateChangeuser(customer.getId());
    }

    @Override
    public void updateStatus(Integer status, Long id) {
        // 改变它状态的时候 就设置修改人
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        // 当有人
        customerMapper.updateStatus(status,id,employee);
    }

    // 修改正式用户
    @Override
    public void updateFormal(Customer record) {
        customerMapper.updateFormal(record);
    }

    @Override
    public void updateChange(Long changeId, Long id) {
            customerMapper.updateChange(changeId, id);
    }

    @Override
    public List<Customer> selectOfficialCustomer() {
        return customerMapper.selectOfficialCustomer();
    }
}
///