package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.BadTable;
import cn.wolfcode.crm.mapper.BadTableMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.BadTableQueryObject;
import cn.wolfcode.crm.service.IBadTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BadTableServiceImpl implements IBadTableService {
    @Autowired
    private BadTableMapper mapper;
    @Autowired
    private BadTableMapper badTableMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(BadTable record) {
        mapper.insert(record);
    }

    @Override
    public BadTable selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BadTable> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(BadTable record) {
        mapper.updateByPrimaryKey(record);
    }


    //高级查询
    public PageResult query(BadTableQueryObject qo) {
        int count=mapper.queryCount(qo);
        if (count<0){
            return new PageResult(0, Collections.emptyList());
        }
        List<BadTable> list=mapper.queryList(qo);
        System.out.println(list);
        System.out.println("=============================================");
        return new PageResult(count,list);
    }

    @Override
    public List<BadTable> getBadListById(Long id) {
        return mapper.getBadListById(id);
    }
}
