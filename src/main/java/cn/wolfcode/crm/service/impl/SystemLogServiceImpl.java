package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by king on 2017/12/17
 */
@Service
@SuppressWarnings("all")
public class SystemLogServiceImpl implements ISystemLogService {
    @Autowired
    private cn.wolfcode.crm.mapper.SystemLogMapper SystemLogMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        SystemLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(SystemLog record) {
        SystemLogMapper.insert(record);
    }

    @Override
    public SystemLog selectByPrimaryKey(Long id) {
        return SystemLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemLog> selectAll() {
        return SystemLogMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(SystemLog record) {
        SystemLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = SystemLogMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<SystemLog> data = SystemLogMapper.queryForList(qo);
        return new PageResult(totalCount, data);
    }
}
