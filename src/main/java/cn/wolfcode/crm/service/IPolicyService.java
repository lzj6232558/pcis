package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Policy;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;

/**
 * created by king on 2017/12/17
 */
public interface IPolicyService {
    void deleteByPrimaryKey(Long id);

    void insert(Policy record, Long[] pIds, Long totalAmount, BigDecimal[] totalMoney, BigDecimal[] annuaFlee);

    Policy selectByPrimaryKey(Long id);

    List<Policy> selectAll();

    void updateByPrimaryKey(Policy record, Long[] pIds, Long totalAmount, BigDecimal[] totalMoney, BigDecimal[] annuaFlee);
    //分页的条件
    PageResult query(QueryObject qo);

    /**
     * 查询状态为0的保单,也就是暂存单
     * @return
     */
    List<Policy> selectTemporary();

    /**
     * 更爱保单的状态
     */
    void changeState(Policy policy);

    void audit(Policy policy);

    /**
     * 根据保单编号查询保单状态
     * @param sn
     * @return
     */
    Policy getStateBySn(String sn);
}
