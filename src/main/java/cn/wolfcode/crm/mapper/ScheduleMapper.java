package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Schedule;
import cn.wolfcode.crm.query.QueryObject;

import java.util.Date;
import java.util.List;

public interface ScheduleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Schedule record);

    Schedule selectByPrimaryKey(Long id);

    List<Schedule> selectAll();

    int updateByPrimaryKey(Schedule record);

    int queryForCount(QueryObject qo);

    List<Schedule> queryForList(QueryObject qo);

    Schedule selectScheduleByTaskId(Long task_id);

    void changeState(Long id);

    List<Long> checkState(Date date);

    void change2UnFinsh(Long id);
}