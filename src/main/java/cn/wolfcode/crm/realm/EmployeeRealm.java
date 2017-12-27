package cn.wolfcode.crm.realm;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.service.IRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * created by king on 2017/12/20
 */

public class EmployeeRealm extends AuthorizingRealm {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Override
    //提供需要认证的对象
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户输入的用户名
        String principal = (String) authenticationToken.getPrincipal();
        //通过这个用户名去数据库中查询对应的员工信息
        Employee employee=employeeService.selectByUsername(principal);
        if (employee==null){
            return null;
        }
        //如果用户存在.那么就去匹配该用户的用户名和密码,这里就相当于shiro拿到用户输入的用户名和密码去和我们数据库中的去查询
        //第三个参数是盐

        return new SimpleAuthenticationInfo(employee,employee.getPassword(),
                ByteSource.Util.bytes(employee.getUsername()),this.getName());
    }
    //每一次判断权限的时候,都会执行这个方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------------------");
        //权限的信息
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        //这里是手动的分配角色和权限
       /* simpleAuthorizationInfo.addRole("mgr");
        simpleAuthorizationInfo.addStringPermission("department:query");*/
       //从数据库中查询登录用户的拥有的权限 得到登录进来的用户
        Employee employee = (Employee)principalCollection.getPrimaryPrincipal();
        //1.判断这个用户是否是管理员,如果是就给他所有的权限
        if (employee.isAdmin()){
            simpleAuthorizationInfo.addRoles(Arrays.asList("admin"));
            simpleAuthorizationInfo.addStringPermission("*:*");
        }else {
            //如果不是管理员,那么就去数据库中查询这个用户拥有的角色和权限并放到他认证的权限信息中
            //1.查询数据库中用户拥有的角色集合
            List<String> roles=roleService.selectRoleByEmpId(employee.getId());
            //2.查询数据库中用户拥有的权限(resource)集合
            List<String> permissions=permissionService.selectPermissionByEmpId(employee.getId());
            simpleAuthorizationInfo.addRoles(roles);
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }
        return simpleAuthorizationInfo;
    }

}
/*
* 缓存的好处就是不会每次都去数据库中去查询用户的角色信息和权限的信息
* */







