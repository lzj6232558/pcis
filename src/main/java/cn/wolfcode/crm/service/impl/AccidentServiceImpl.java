package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Accident;
import cn.wolfcode.crm.mapper.AccidentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AccidentQueryObject;
import cn.wolfcode.crm.service.IAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccidentServiceImpl implements IAccidentService{
    @Autowired
    private AccidentMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Accident record) {
        mapper.insert(record);
    }

    @Override
    public Accident selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Accident> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Accident record) {
        mapper.updateByPrimaryKey(record);

    }

    //高级查询
    public PageResult query(AccidentQueryObject qo) {
        int count=mapper.queryCount(qo);
        if (count<0){
            return new PageResult(0, Collections.emptyList());
        }
        List<Accident> list=mapper.queryList(qo);
        return new PageResult(count,list);
    }
}
