package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Enquiry;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;

/**
 * created by king on 2017/12/17
 */
public interface IEnquiryService {
    void deleteByPrimaryKey(Long id);

    void insert(Enquiry record,Long[] productIds, BigDecimal[] totalMoney, BigDecimal[] annuafe);

    Enquiry selectByPrimaryKey(Long id);

    List<Enquiry> selectAll();

    void updateByPrimaryKey(Enquiry record, Long[] productIds, BigDecimal[] totalMoney, BigDecimal[] annuafee);
    //分页的条件
    PageResult query(QueryObject qo);

    void saveBill(Long id);
}
