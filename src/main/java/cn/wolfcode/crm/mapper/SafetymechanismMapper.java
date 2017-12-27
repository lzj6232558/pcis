package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Safetymechanism;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface SafetymechanismMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Safetymechanism record);

    Safetymechanism selectByPrimaryKey(Long id);

    List<Safetymechanism> selectAll();

    void updateByPrimaryKey(Safetymechanism record);

    int queryCount(QueryObject qo);

    List<Safetymechanism> queryList(QueryObject qo);

    Safetymechanism selectSafByEnqId(Long enqId);
}