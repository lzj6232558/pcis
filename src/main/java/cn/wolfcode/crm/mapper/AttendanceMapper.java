package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Attendance;
import cn.wolfcode.crm.query.AttendanceQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attendance record);

    Attendance selectByPrimaryKey(Long id);

    List<Attendance> selectAll();

    int updateByPrimaryKey(Attendance record);

    int queryForyCount(AttendanceQueryObject qo);

    List<Attendance> queryForList(AttendanceQueryObject qo);

    void signIn(Attendance record);

    Attendance checkSign(@Param("data") Date date, @Param("employee_id") Long id);

    void signOut(Attendance record);

    void resignIn(Attendance attendance);

    List<Attendance> checkState();

}