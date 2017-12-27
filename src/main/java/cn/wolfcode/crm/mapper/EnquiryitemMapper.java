package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Enquiryitem;

import java.util.List;

public interface EnquiryitemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Enquiryitem record);

    Enquiryitem selectByPrimaryKey(Long id);

    List<Enquiryitem> selectAll();

    int updateByPrimaryKey(Enquiryitem record);

    java.util.List<Enquiryitem> selectTotalMoneyByProId(Long productId);

    java.util.List<Enquiryitem> selectItemsByEnqId(Long enqId);
}
