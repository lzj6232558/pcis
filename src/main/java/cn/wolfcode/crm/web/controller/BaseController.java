package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.utils.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by king on 2017/12/20
 */
@ControllerAdvice
public class BaseController {
    //统一的异常处理
    @ExceptionHandler(AuthorizationException.class)
    public void handleException(HandlerMethod method, HttpServletResponse response) throws IOException {
        ResponseBody annotation = method.getMethodAnnotation(ResponseBody.class);
        if(annotation!=null){
            //如果是ajax请求,那么就响应一个json字符串
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult("您没有权限!")));
        }else{
            //如果不是ajax请求,那么就返回一个没有权限的页面
            response.sendRedirect("/nopermission.jsp");
        }
    }
}
