package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Customer;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Policy;
import cn.wolfcode.crm.domain.PolicyDetail;
import cn.wolfcode.crm.mapper.CarMapper;
import cn.wolfcode.crm.mapper.CustomerMapper;
import cn.wolfcode.crm.mapper.PolicyDetailMapper;
import cn.wolfcode.crm.mapper.PolicyMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPolicyService;
import cn.wolfcode.crm.utils.DateUtil;
import cn.wolfcode.crm.utils.SerialNumber;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * created by king on 2017/12/18
 */
@Service
@SuppressWarnings("all")
public class PolicyServiceImpl implements IPolicyService {
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private PolicyDetailMapper policyDetailMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        policyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Policy record, Long[] pIds, Long totalAmount, BigDecimal[] totalMoney, BigDecimal[] annuaFlee) {

        if (record.getCustomer().getId()==null) {
            Customer customer = record.getCustomer();
            customer.setStatus(2);
            customerMapper.insert(customer);
        }else {
            //customerMapper.updateByPrimaryKey(record.getCustomer());
        }
        if (record.getCar().getId()==null){
            carMapper.insert(record.getCar());
        }else {
           // carMapper.updateByPrimaryKey(record.getCar());
        }
        record.setApplyDate(new Date());
        record.setBeginDate(new Date());
        record.setSn(SerialNumber.Getnum());
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        record.setInputUser(employee);
        int duration = record.getDuration();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(DateUtil.getEndDate(new Date()));
        calendar.add(Calendar.YEAR, duration);
        //插入保单的结束时间
        record.setEndDate(calendar.getTime());
        policyMapper.insert(record);
        //插入到投保单明细表中
        PolicyDetail policyDetail = new PolicyDetail();
        policyDetail.setPolicy(record);
        policyDetail.setAmount(totalAmount);
        policyDetail.setRemark(record.getRemark());
        //插入到订单明细表中
        //policyDetailMapper.insert(policyDetail);
        //插入到保单表和产品表的中间表中
        if (pIds != null) {
            for (int index = 0; index < pIds.length; index++) {
                policyMapper.insertPolicyProductRelation(pIds[index], record.getId(), totalMoney[index], annuaFlee[index]);
            }

        }
    }

    @Override
    public Policy selectByPrimaryKey(Long id) {
        return policyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Policy> selectAll() {
        return policyMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Policy record, Long[] pIds, Long totalAmount, BigDecimal[] totalMoney, BigDecimal[] annuaFlee) {
        record.setApplyDate(new Date());
        record.setBeginDate(new Date());
        record.setSn(SerialNumber.Getnum());
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        record.setInputUser(employee);
        int duration = record.getDuration();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.getEndDate(new Date()));
        calendar.add(Calendar.YEAR, duration);
        //插入保单的结束时间
        record.setEndDate(calendar.getTime());

        //--------
        //先打破关系,再插入
        policyMapper.deletePolicyProductRelation(record.getId());
        if (pIds != null) {
            for (int index = 0; index < pIds.length; index++) {
                policyMapper.insertPolicyProductRelation(pIds[index], record.getId(), totalMoney[index], annuaFlee[index]);
            }

        }
        //跟新保单表
        policyMapper.updateByPrimaryKey(record);
        PolicyDetail policyDetail = new PolicyDetail();
        policyDetail.setPolicy(record);
        policyDetail.setAmount(totalAmount);
        policyDetail.setRemark(record.getRemark());
        //更新明细表
       // policyDetailMapper.updateByPrimaryKey(policyDetail);
    }


    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = policyMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Policy> data = policyMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

    /**
     * 查询暂存单
     *
     * @return
     */
    @Override
    public List<Policy> selectTemporary() {
        return policyMapper.selectTemporary();
    }

    /**
     * 更改保单的状态
     *
     * @param state
     */
    @Override
    public void changeState(Policy policy) {
        policyMapper.changeState(policy);
    }

    /**
     * 审核保单
     *
     * @param policy
     */
    @Override
    public void audit(Policy policy) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        policy.setAuditor(employee);
        //设置审核日期
        policy.setCheckDate(new Date());
        policyMapper.audit(policy);
    }

    //根据保单编号查询保单状态
    public Policy getStateBySn(String sn) {
        return policyMapper.getStateBySn(sn);
    }

}
