package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Cases;
import cn.wolfcode.crm.mapper.CasesMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by king on 2017/12/18
 */
@Service
@SuppressWarnings("all")
public class CasesServiceImpl implements ICasesService {
    @Autowired
    private CasesMapper casesMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        casesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Cases record) {

        casesMapper.insert(record);
    }

    @Override
    public Cases selectByPrimaryKey(Long id) {
        return casesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Cases> selectAll() {
        return casesMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Cases record) {
        casesMapper.updateByPrimaryKey(record);
    }


    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = casesMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Cases> data = casesMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }

}
