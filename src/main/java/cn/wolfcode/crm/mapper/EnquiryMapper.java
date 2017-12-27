package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Enquiry;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EnquiryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Enquiry record);

    Enquiry selectByPrimaryKey(Long id);

    List<Enquiry> selectAll();

    int updateByPrimaryKey(Enquiry record);

    /**
     * 维护中间表的关系
     * @param id
     * @param productId
     */
    void insertEnquireProductRelation(@Param("enquiryId") Long id, @Param("productId") Long productId);

    /**
     * 分页
     * @param qo
     * @return
     */
    int queryForCount(QueryObject qo);

    List<Enquiry> queryForList(QueryObject qo);
    List<Enquiry> queryForList2(Long id);

    void deleteEnquiryProductRelationByEnqId(Long id);

    void insertRelation(@Param("enqId") Long enqId,@Param("productId") Long productId, @Param("totalMoney") BigDecimal bigDecimal, @Param("annuafee") BigDecimal bigDecimal1);

    /**
     * 跟新询价单的状态为1
     * @param id
     */
    void updateState(Long id);
}