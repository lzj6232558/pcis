package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.FeePolicy;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IFeePolicyService {
    int deleteByPrimaryKey(Long id);

    int insert(FeePolicy record);

    FeePolicy selectByPrimaryKey(Long id);

    List<FeePolicy> selectAll();

    int updateByPrimaryKey(FeePolicy record);

    PageResult query(QueryObject qo);

    /**
     * policySn自动补全查询
     * @param param 查询参数
     * @return
     */
    List<String> autoSearchPolicySn(String param);

    List<String> autoSearchKeywords(String param);

}
