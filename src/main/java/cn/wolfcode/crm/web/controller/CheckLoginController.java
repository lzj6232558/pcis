package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * created by king on 2017/11/30
 */
@Controller
public class CheckLoginController {
    @Autowired
    IEmployeeService employeeService;
    //如果来到这里说明登录出现了问题
    @RequestMapping("login")
    public String login(HttpServletRequest request) throws Exception {
        //来到这里就是出错了
        return "forward:login.jsp";
    }

    @RequestMapping("main")
    public String main() throws Exception {
        return "main2";
    }
}
