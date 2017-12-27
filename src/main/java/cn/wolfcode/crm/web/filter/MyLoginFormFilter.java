package cn.wolfcode.crm.web.filter;

import cn.wolfcode.crm.utils.JsonResult;
import cn.wolfcode.crm.utils.RequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * created by king on 2017/12/20
 */
@Component("myFormFilter")
public class MyLoginFormFilter extends FormAuthenticationFilter {

    protected boolean onLoginSuccess(AuthenticationToken token,
                                     Subject subject, ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletResponse resp= (HttpServletResponse)response;
        resp.getWriter().print(
                new ObjectMapper().writeValueAsString(new JsonResult(true,"登录成功")));
        return false;
    }

    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request,
                                     ServletResponse response) {
        HttpServletResponse resp= (HttpServletResponse)response;
        JsonResult result = null;
        if (e.getClass().getName()
                .equals(UnknownAccountException.class.getName())) {
            result = new JsonResult( "用户名不存在!");
        } else if (e.getClass().getName()
                .equals(IncorrectCredentialsException.class.getName())) {
            result = new JsonResult( "密码错误!");
        }
        try {
            resp.getWriter().print(
                    new ObjectMapper().writeValueAsString(result));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
        return false;
    }
    //解决不能重复登录的问题
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如这个方法是所有的url路径都要经过的,如果是登录请求,那么才去清除subject中的数据
        //把ServletRequest设置本地变量
        RequestUtil.setRequest(request);
        //如果是登录请求,并且已经登录过,那么就注销之前登录过的
        if (isLoginRequest(request,response)){
            Subject subject = SecurityUtils.getSubject();
            if(subject.isAuthenticated()){
                //如果验证过,那么就去清除登录的主体信息
                subject.logout();
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}

