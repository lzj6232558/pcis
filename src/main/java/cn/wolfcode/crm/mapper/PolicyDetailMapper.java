package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.PolicyDetail;
import java.util.List;

public interface PolicyDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PolicyDetail record);

    PolicyDetail selectByPrimaryKey(Long id);

    List<PolicyDetail> selectAll();

    int updateByPrimaryKey(PolicyDetail record);
}