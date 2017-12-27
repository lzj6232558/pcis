package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Safetymechanism;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.SafeQueryObject;

import java.util.List;

public interface ISafetymechanismService {
    void deleteByPrimaryKey(Long id);

    void insert(Safetymechanism record);

    Safetymechanism selectByPrimaryKey(Long id);

    List<Safetymechanism> selectAll();

    void updateByPrimaryKey(Safetymechanism record);
    PageResult query(SafeQueryObject qo);
}
