package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Policy;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;


public interface IPaymentService {

    /**
     * 根据保单查询所买的产品
     * @param policyId
     * @return
     */
    List<Product> getProducts(Long policyId);

    /**
     * 缴费方式
     * @param id
     * @param paymentWay 收费方式
     */
    void pay(Long  id, Integer paymentWay);

    /**
     * 退回到待修改状态
     * @param id
     */
    void changeState(Long id);
   /* void deleteByPrimaryKey(Long id);

    void insert(Policy record, Long[] pIds, Long totalAmount);
d
    Policy selectByPrimaryKey(Long id);

    List<Policy> selectAll();

    void updateByPrimaryKey(Policy record, Long[] pIds, Long totalAmount);
    //分页的条件
    PageResult query(QueryObject qo);

    *//**
     * 查询状态为0的保单,也就是暂存单
     * @return
     *//*
    List<Policy> selectTemporary();

    *//**
     * 更爱保单的状态
     *//*
    void changeState(Policy policy);

    void audit(Policy policy);*/
}
