package cn.wolfcode.crm.utils;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.service.ISystemLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import java.util.Date;

/**
 * 系统日志的工具类
 */

public class SystemLogUtils {
    @Autowired
    private ISystemLogService logService;
    public void writeLog(JoinPoint joinPoint) {
        if (joinPoint.getTarget() instanceof ISystemLogService){
            return;
        }
        System.out.println("日志-------------");
        SystemLog log = new SystemLog();
        //得到当前登录的主体
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        log.setOpuser(employee);
        //设置时间
        log.setOpTime(new Date());
        //设置function:类的全限定名+方法名/
        //获取当前访问的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取当前访问的controller的方法
        String methodName = joinPoint.getSignature().getName();
        //设置日志的function  例如:com._520it.crm.service.impl.EmployeeServiceImpl:selectByUserName
        log.setFunction(className + ":" + methodName);
        try {
            //设置日志的参数,json格式
            String params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
            //log.setParams(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //设置日志的ip,从请求对象中
        ServletRequest request = RequestUtil.getRequest();
        if (request!=null){
            //获取远程访问的ip地址
            log.setOpIp(request.getRemoteAddr());
        }
        logService.insert(log);
    }
}
