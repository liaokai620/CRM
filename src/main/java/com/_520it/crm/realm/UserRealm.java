package com._520it.crm.realm;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.service.IRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyhzzz
 * @date 2017/11/7
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService   employeeService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService       roleService;

    @Override
    public String getName() {
        return "UserRealm";
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Employee current = employeeService.queryForUsername(username);
        if (current == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(current, current.getPassword(), getName());
        return info;
    }


    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Employee current = (Employee) principals.getPrimaryPrincipal();
        List<String> permissions = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        if (current.isAdmin()) {
            permissions.add("*:*");
            List<Role> roleList = roleService.selectAll();
            for (Role role : roleList) {
                roles.add(role.getSn());

            }

        }else{
            List<Role> roleList = roleService.queryForEmployee(current.getId());
            for (Role role : roleList) {
                roles.add(role.getSn());
            }
            List<Permission> permissionList = permissionService.queryForEmployee(current.getId());
            for (Permission permission : permissionList) {
                permissions.add(permission.getResource());
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }


}
