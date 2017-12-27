package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Policy;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PolicyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Policy record);

    Policy selectByPrimaryKey(Long id);

    List<Policy> selectAll();

    int updateByPrimaryKey(Policy record);

    int queryForCount(QueryObject qo);

    List<Policy> queryForList(QueryObject qo);

    /**
     * 维护订单表和产品表的关系
     * @param pId
     * @param id
     */
    void insertPolicyProductRelation(@Param("productId") Long pId, @Param("policyId") Long id, @Param("totalMoney") BigDecimal totalMoney, @Param("annuaFlee") BigDecimal annuaFlee);

    /**
     * 查询暂存单
     * @return
     */
    List<Policy> selectTemporary();

    void deletePolicyProductRelation(Long id);

    /**
     * 更改保单的我状态
     *
     */
    void changeState(Policy policy);

    /**
     * 审核表单
     * @param policy
     */
    void audit(Policy policy);

    /**
     * 根据保单编号查询保单状态
     * @param sn
     * @return
     */
    Policy getStateBySn(String sn);
}