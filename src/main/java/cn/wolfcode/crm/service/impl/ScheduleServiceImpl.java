package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Attendance;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Schedule;
import cn.wolfcode.crm.domain.Task;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.ScheduleMapper;
import cn.wolfcode.crm.mapper.TaskMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.ScheduleQueryObject;
import cn.wolfcode.crm.service.IScheduleService;
import cn.wolfcode.crm.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //下面删除任务详情
        taskMapper.deleteByPrimaryKey(scheduleMapper.selectByPrimaryKey(id).getTask().getId());
        return scheduleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Task record) throws Exception {
        Date date = new Date();
        //保存具体任务
        taskMapper.insert(record);
        //保存任务列表
        Schedule schedule = new Schedule();
        schedule.setCreateDate(DateUtil.getCurrentDate(date));//设置任务创建时间
        schedule.setTask(record);//设置任务详情
        schedule.setEmployee(record.getEmployee());//设置员工
        Employee employee = employeeMapper.selectByPrimaryKey(record.getEmployee().getId());
        schedule.setDepartment(employee.getDept());//设置部门
        scheduleMapper.insert(schedule);
        return 1;
    }

    @Override
    public Schedule selectByPrimaryKey(Long id) {
        return scheduleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Schedule> selectAll() {
        return scheduleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Task record) {
        //保存具体任务
        taskMapper.updateByPrimaryKey(record);
        //保存任务列表
        Schedule schedule = scheduleMapper.selectScheduleByTaskId(record.getId());
        schedule.setTask(record);//设置任务详情
        schedule.setEmployee(record.getEmployee());//修改员工
        Employee employee = employeeMapper.selectByPrimaryKey(record.getEmployee().getId());
        schedule.setDepartment(employee.getDept());//修改部门
        scheduleMapper.updateByPrimaryKey(schedule);
        return 1;
    }


    @Override
    public PageResult query(QueryObject qo) {
        int count = scheduleMapper.queryForCount(qo);
        if (count == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Schedule> list = scheduleMapper.queryForList(qo);
        return new PageResult(count, list);
    }

    @Override
    public void changeState(Long id) {
        scheduleMapper.changeState(id);
    }

    @Override
    public void checkState() throws Exception {
        Date date = DateUtil.getCurrentDate(new Date());
        List<Long> ids = scheduleMapper.checkState(date);
        if (ids != null) {
            for (Long id : ids) {
                scheduleMapper.change2UnFinsh(id);
            }
        }
    }
}
