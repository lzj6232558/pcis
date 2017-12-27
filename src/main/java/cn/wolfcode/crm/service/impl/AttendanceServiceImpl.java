package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Attendance;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.AttendanceMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.AttendanceQueryObject;
import cn.wolfcode.crm.service.IAttendanceSerivce;
import cn.wolfcode.crm.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements IAttendanceSerivce {

    @Autowired
    AttendanceMapper attendanceMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return attendanceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Attendance record) {
        return attendanceMapper.insert(record);
    }

    @Override
    public Attendance selectByPrimaryKey(Long id) {
        return attendanceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Attendance> selectAll() {
        return attendanceMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Attendance record) {
        return attendanceMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(AttendanceQueryObject qo) {
        int count = attendanceMapper.queryForyCount(qo);
        if (count == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<Attendance> list = attendanceMapper.queryForList(qo);
        return new PageResult(count, list);
    }

    @Override
    public JsonResult signIn(Employee employee, HttpServletRequest request) {
        Date date = new Date();
        try {
            Attendance attendance = new Attendance();
            attendance.setSignInDate(getCurrentDateTime(date));

            //设置签到客户端的ip
            attendance.setSignInIP(getIpAddr(request));
            //设置签到员工
            attendance.setEmployee(employee);
            /**
             * 检查当前用户是否已经签到,
             * 如果已经登陆:就不再允许签到
             */
            Attendance temp = attendanceMapper.checkSign(getCurrentDate(date), employee.getId());
            if (temp != null) {
                return new JsonResult("你已经签到过,请勿重复签到!");
            }
            attendanceMapper.signIn(attendance);
            return new JsonResult(true,"签到成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"签到失败,请重新签到!");
        }
    }

    @Override
    public JsonResult signOut(Employee employee) {

        Date date = new Date();
        try {
            /**
             * 查看是否有签到记录,如果没有,就无法签到
             */
            Attendance attendance = attendanceMapper.checkSign(getCurrentDate(date), employee.getId());
            if (attendance == null) {
                return new JsonResult("您今天还有签到,请先签到!");
            }
            //设置(修改)签退时间
            attendance.setSignOutDate(getCurrentDateTime(date));
            //设置状态,如果早退就为异常
            //设置异常信息
            attendanceMapper.signOut(attendance);
            return new JsonResult("签退成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("签退失败,请重新签退!");
        }
    }

    @Override
    public JsonResult resignIn(Employee employee, Long id) {
        Date date = new Date();
        try {
            Attendance attendance = attendanceMapper.selectByPrimaryKey(id);
            //设置补签时间
            attendance.setResignInDate(getCurrentDateTime(date));
            //设置补签员工
            attendance.setResignInEmployee(employee);
            attendanceMapper.resignIn(attendance);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult("补签失败,请重新补签!");
        }
    }

    @Override
    public void checkState() {
        List<Attendance> attendances = attendanceMapper.checkState();
        for (Attendance attendance : attendances) {
            attendance.setState(true);
            attendanceMapper.updateByPrimaryKey(attendance);
        }
    }


    //获取时间和日期
    private Date getCurrentDateTime(Date date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(date);
        return df.parse(format);
    }

    //获取日期
    private Date getCurrentDate(Date date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(date);
        return df.parse(format);
    }

    //获取时间
    private Date getCurrentTime(Date date) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String format = df.format(date);
        return df.parse(format);
    }

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
