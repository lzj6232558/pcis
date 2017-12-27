package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.FeePolicy;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.query.FeePolicyQueryObject;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface FeePolicyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FeePolicy record);

    FeePolicy selectByPrimaryKey(Long id);

    List<FeePolicy> selectAll();

    int updateByPrimaryKey(FeePolicy record);

    int queryForyCount(QueryObject qo);

    List<FeePolicy> queryForList(QueryObject qo);

    /**
     * policySn自动补全查询
     *
     * @param param
     * @return
     */
    List<String> autoSearchPolicySn(String param);


    List<String> autoSearchName(String param);

    List<String> autoSearchIdno(String param);

    /**
     * 通过保单号id查询出订购的产品
     * @param policyId
     * @return
     */
    List<Product> getProducts(Long policyId);
}