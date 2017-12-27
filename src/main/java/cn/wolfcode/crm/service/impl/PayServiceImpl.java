package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.FeePolicyMapper;
import cn.wolfcode.crm.mapper.PolicyMapper;
import cn.wolfcode.crm.mapper.ProductMapper;
import cn.wolfcode.crm.query.PolicyQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPaymentService;
import cn.wolfcode.crm.utils.SerialNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by king on 2017/12/18
 */
@Service
@SuppressWarnings("all")
public class PayServiceImpl implements IPaymentService {
    @Autowired
    private PolicyMapper policyMapper;
    @Autowired
    private FeePolicyMapper feePolicyMapper;
    // @Autowired
    private IPaymentService paymentService;

    @Override
    public List<Product> getProducts(Long policyId) {
        return feePolicyMapper.getProducts(policyId);
    }

    @Override
    public void pay(Long  id, Integer paymentWay) {
        Policy policy1 = policyMapper.selectByPrimaryKey(id);
        PolicyQueryObject policyQueryObject = new PolicyQueryObject();
        policyQueryObject.setState(2);
        policyQueryObject.setPolicyNo(policy1.getSn());
        Policy policy = policyMapper.queryForList(policyQueryObject).get(0);
        //更新保单状态
        policy.setState(4);
        policyMapper.changeState(policy);
        //保存收费记录
        FeePolicy feePolicy = new FeePolicy();
        feePolicy.setCustomer(policy.getCustomer());
        feePolicy.setTotalAmount(policy.getTotalAmount());
        feePolicy.setPaymentWay(paymentWay);
        feePolicy.setPaymentWay(1);
        feePolicy.setState(1);
        feePolicy.setBeginDate(policy.getBeginDate());
        feePolicy.setEndDate(policy.getEndDate());
        feePolicy.setInputUser(policy.getInputUser());
        feePolicy.setSafetymechanism(policy.getSafetymechanism());
        feePolicy.setPolicySn(policy.getSn());
        //设置缴费流水号
        feePolicy.setSerialNumber(SerialNumber.GetSerialNumber());
        feePolicyMapper.insert(feePolicy);
    }

    @Override
    public void changeState(Long id) {
        Policy policy = policyMapper.selectByPrimaryKey(id);
        //更新保单状态
        policy.setState(3);
        policyMapper.changeState(policy);
    }


}
