package cn.wolfcode.crm.utils;

import java.lang.reflect.Method;

/**
 * created by king on 2017/11/29
 */
public abstract class PermissionUtils {

    //得到权限表达式的方法
    public static String getExpression(Method method){
        String controllerName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        return controllerName+":"+methodName;
    }
}
