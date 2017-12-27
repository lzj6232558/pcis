package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Enquiryitem;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.domain.Safetymechanism;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll(Long id);

    void updateByPrimaryKey(Product record);

    int queryCount(QueryObject qo);

    List<Product> queryList(QueryObject qo);

    List<Product> selectProductByPolicyId(Long id);

    Safetymechanism selectSafetymechanismByPolicyId(Long policyId);

    List<Product> selectProductByItemId(@Param("id") Long id,@Param("enquiryId") Long enquiryId);

    void updateTotalMoney(Map<String,Object> map);

    /**
     * 根据询价单的id查询所有的保险产品信息
     * @param enqId
     * @return
     */
    List<Product> selectProByEnqId(Long enqId);

    /**
     *
     * @param enquiryId
     * @return
     */
    List<Enquiryitem> selectTotalMoney(Long enquiryId);

    int selectByProductIdAndenquiryId(@Param("id") Long id, @Param("enquiryId") Long enquiryId);

    void insertRelation(Map<String, Object> map);

    void updateTotalMoney2(Product product);

    List<Product> selectProductByItemId2(Long id);
    void updateTotalMoney(Product product);



    List<Product> list();
}