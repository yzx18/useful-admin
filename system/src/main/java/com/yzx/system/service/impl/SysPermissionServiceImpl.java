package com.yzx.system.service.impl;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.utils.UserUtils;
import com.yzx.system.service.ISysMenuService;
import com.yzx.system.service.ISysPermissionService;
import com.yzx.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: xz-framework-parent-reversion
 * @description: 系统用户权限业务逻辑封装
 * @author: wdw
 * @create: 2020-02-13 14:43
 **/
@Service
public class SysPermissionServiceImpl  implements ISysPermissionService {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param userId 用户ID
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (UserUtils.isAdmin(userId)) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户ID
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (UserUtils.isAdmin(userId)) {
            roles.add("*:*:*");
        } else {
            roles.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return roles;
    }
}
