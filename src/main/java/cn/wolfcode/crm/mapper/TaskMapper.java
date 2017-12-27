package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Task;
import java.util.Date;import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    Task selectByPrimaryKey(Long id);

    List<Task> selectAll();

    int updateByPrimaryKey(Task record);


}