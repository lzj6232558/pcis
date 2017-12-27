package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Safetymechanism;
import cn.wolfcode.crm.mapper.SafetymechanismMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.SafeQueryObject;
import cn.wolfcode.crm.service.ISafetymechanismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SafetymechanismServiceImpl implements ISafetymechanismService{
    @Autowired
    private SafetymechanismMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);

    }

    @Override
    public void insert(Safetymechanism record) {
        mapper.insert(record);

    }

    @Override
    public Safetymechanism selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Safetymechanism> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Safetymechanism record) {
        mapper.updateByPrimaryKey(record);

    }
    @Override
    public PageResult query(SafeQueryObject qo) {
        int count=mapper.queryCount(qo);
        if (count<0){
            return new PageResult(0, Collections.emptyList());
        }
        List<Safetymechanism> list=mapper.queryList(qo);
        return new PageResult(count,list);
    }
}
