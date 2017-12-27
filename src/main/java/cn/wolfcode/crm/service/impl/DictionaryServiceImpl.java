package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.mapper.DictionaryMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/12/18
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private DictionaryMapper DictionaryMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        DictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Dictionary record) {

        DictionaryMapper.insert(record);
    }

    @Override
    public Dictionary selectByPrimaryKey(Long id) {
        return DictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dictionary> selectAll() {
        return DictionaryMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Dictionary record) {
        DictionaryMapper.updateByPrimaryKey(record);
    }


    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = DictionaryMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Dictionary> data = DictionaryMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

    @Override
    public List<Map<String, Object>> selectDictionary() {
        return DictionaryMapper.selectDictionary();
    }

}
