package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.SystemMenu;
import cn.wolfcode.crm.service.ISystemMenuService;
import cn.wolfcode.crm.utils.PermissionMenuUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * created by king on 2017/11/27
 */
@Controller
@RequestMapping("systemMenu")
public class SystemMenuController {
    private static final String MENU_LIST = "menu_list";
    @Autowired
    private ISystemMenuService systemMenuService;

    //异步加载数据,根据parentSn从数据库中查询子菜单的url
    //动态的去加载菜单的信息
    //{ id:11, pId:1, name:"部门管理",controller:"/department/query.do"}, 这是返回的json的格式
   /* @RequestMapping("selectMenuByParentId")
    @ResponseBody
    public List<Map<String, Object>> selectMenuByParentId(String parentSn) throws Exception {
        return systemMenuService.selectMenuByParentId(parentSn);
    }*/
    //初始化菜单树
    @ResponseBody
    @RequestMapping("getMenu")
    public List<SystemMenu> getMenu() {
        Session session = SecurityUtils.getSubject().getSession();
        List<SystemMenu> menuList = (List<SystemMenu>) session.getAttribute(MENU_LIST);
        if (menuList == null) {
            //我们这里查询到的是子菜单和父菜单在一起的一个集合,我们又得边迭代别删除,那么我们就得用迭代器
            List<SystemMenu> menus = systemMenuService.getMenu();
            PermissionMenuUtils.fileterPermissionMenu(menus);
            session.setAttribute(MENU_LIST, menus);
            return menus;
        }
        return menuList;
    }

    @ResponseBody
    @RequestMapping("getChildrenMenu")
    public List<SystemMenu> getChildrenMenu(Long parent_id) {
        /**
         * 菜单权限已注释
         */
        //Session session = SecurityUtils.getSubject().getSession();
        //List<SystemMenu> menuList = (List<SystemMenu>) session.getAttribute(MENU_LIST);
        //if (menuList == null) {
        //我们这里查询到的是子菜单和父菜单在一起的一个集合,我们又得边迭代别删除,那么我们就得用迭代器
        List<SystemMenu> menus = systemMenuService.getChildrenMenu(parent_id);
        //    PermissionMenuUtils.fileterPermissionMenu(menus);
        //   session.setAttribute(MENU_LIST, menus);
        return menus;
        //}
        //return menuList;
    }
}
















