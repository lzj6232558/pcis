package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.mapper.DictionaryItemMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by king on 2017/12/18
 */
@Service
public class DictionaryItemServiceImpl implements IDictionaryItemService {
    @Autowired
    private DictionaryItemMapper DictionaryItemMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        DictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(DictionaryItem record) {

        DictionaryItemMapper.insert(record);
    }

    @Override
    public DictionaryItem selectByPrimaryKey(Long id) {
        return DictionaryItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DictionaryItem> selectAll() {
        return DictionaryItemMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(DictionaryItem record) {
        DictionaryItemMapper.updateByPrimaryKey(record);
    }


    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = DictionaryItemMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<DictionaryItem> data = DictionaryItemMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

    @Override
    public List<DictionaryItem> select(String sn) {
        return DictionaryItemMapper.select(sn);
    }
}
