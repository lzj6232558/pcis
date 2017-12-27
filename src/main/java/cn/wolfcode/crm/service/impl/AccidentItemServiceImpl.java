package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.AccidentItem;
import cn.wolfcode.crm.domain.BadTable;
import cn.wolfcode.crm.mapper.AccidentItemMapper;
import cn.wolfcode.crm.mapper.BadTableMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AccidentItemQueryObject;
import cn.wolfcode.crm.service.IAccidentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccidentItemServiceImpl implements IAccidentItemService {
    @Autowired
    private AccidentItemMapper mapper;
    @Autowired
    private BadTableMapper badTableMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        //删除赔偿表:
        badTableMapper.deleteByPrimaryKey(id);
        //删除自己:
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(AccidentItem record) {
        mapper.insert(record);
    }

    @Override
    public AccidentItem selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AccidentItem> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(AccidentItem record) {
        //01:先修改数据:
        mapper.updateByPrimaryKey(record);
        //02:添加明细:
        for (BadTable badTable : record.getBadTables()) {
            badTable.setAccidentItemId(record);
            badTableMapper.insert(badTable);
        }
    }

    //高级查询
    public PageResult query(AccidentItemQueryObject qo) {
        int count=mapper.queryCount(qo);
        if (count<0){
            return new PageResult(0, Collections.emptyList());
        }
        List<AccidentItem> list=mapper.queryList(qo);
        return new PageResult(count,list);
    }
}
