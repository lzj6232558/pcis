package cn.wolfcode.crm.utils;

import javax.servlet.ServletRequest;

/**
 * 为systemLogUtils服务的获取ServletRequest请求对象的工具类
 */
public class RequestUtil {
    //定义一个ThreadLocal对象,(每个ThreadLocal对象对应一个要存储的对象)
    private static ThreadLocal<ServletRequest> threadLocal=new ThreadLocal<>();

    //在MyLoginFormFilter中设置线程本地变量的request对象(由于每个请求都会经过MyLoginFormFilter.isAccessAllowed()方法)
    public static void setRequest(ServletRequest request){
        threadLocal.set(request);
    }
    //得到放在线程本地变量的request对象
    public static ServletRequest getRequest(){
       return threadLocal.get();
    }
}
