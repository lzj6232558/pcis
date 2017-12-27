package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Schedule;
import cn.wolfcode.crm.domain.Task;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.ScheduleQueryObject;

import java.util.Date;
import java.util.List;

public interface IScheduleService {
    int deleteByPrimaryKey(Long id);

    int insert(Task record) throws Exception;

    Schedule selectByPrimaryKey(Long id);

    List<Schedule> selectAll();

    int updateByPrimaryKey(Task record);

    PageResult query(QueryObject qo);

    /**
     * 标记为完成
     * @param id 任务id
     */
    void changeState(Long id);

    /**
     * 检查未完成任务
     */
    void checkState() throws Exception;
}
