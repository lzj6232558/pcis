package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Task;

import java.util.List;

public interface ITaskService {
    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    Task selectByPrimaryKey(Long id);

    List<Task> selectAll();

    int updateByPrimaryKey(Task record);


}
