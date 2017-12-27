package cn.wolfcode.crm.utils;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.domain.SystemMenu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Iterator;
import java.util.List;

/**
 * created by king on 2017/12/20
 */
//检查菜单的工具类
public abstract class PermissionMenuUtils {


    public static void fileterPermissionMenu(List<SystemMenu> menus) {
        //得到主体对象,因为我们在realm进行认证的时候是放了employee对象,授权的时候也对他进行了一系列的角色和权限的授权
        Subject subject = SecurityUtils.getSubject();
        Iterator<SystemMenu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            SystemMenu menu = iterator.next();
            System.out.println(menu.getText());
            Permission permission = menu.getPermission();
            if (permission != null) {
                //如果菜单上有需要的权限才能访问
                //如果主体中没有菜单上的权限,那么就删除这个菜单
                if (!subject.isPermitted(permission.getResource())) {
                    iterator.remove();
                    System.out.println(menu);
                    continue;
                }
            }
            //如果这个菜单有子菜单,那么就递归的去检查这个菜单有没有权限
            if (menu.getChildren().size()>0){
                PermissionMenuUtils.fileterPermissionMenu(menu.getChildren());
            }
        }
    }
}
