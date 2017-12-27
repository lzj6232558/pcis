package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Task;
import cn.wolfcode.crm.mapper.TaskMapper;
import cn.wolfcode.crm.service.ITaskService;
import cn.wolfcode.crm.utils.DateUtil;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return taskMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Task record) {
        return taskMapper.insert(record);
    }

    @Override
    public Task selectByPrimaryKey(Long id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Task> selectAll() {
        return taskMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Task record) {
        return taskMapper.updateByPrimaryKey(record);
    }

}
