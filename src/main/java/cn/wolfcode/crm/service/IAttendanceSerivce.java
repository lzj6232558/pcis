package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Attendance;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AttendanceQueryObject;
import cn.wolfcode.crm.utils.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAttendanceSerivce {
    int deleteByPrimaryKey(Long id);

    int insert(Attendance record);

    Attendance selectByPrimaryKey(Long id);

    List<Attendance> selectAll();

    int updateByPrimaryKey(Attendance record);

    PageResult query(AttendanceQueryObject qo);

    /**
     * 签到
     *
     * @param employee 当前员工对象
     * @return
     */
    JsonResult signIn(Employee employee, HttpServletRequest request);

    /**
     * 签退
     *
     * @param employee 登陆的员工
     * @return
     */
    JsonResult signOut(Employee employee);

    /**
     * 补签
     *
     * @param employee 登陆操作的员工
     * @param id       补签数据的编号
     * @return
     */
    JsonResult resignIn(Employee employee, Long id);

    /**
     * 检查状态是否异常
     */
    void checkState();
}
