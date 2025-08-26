package com.yzx.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * @program: xz-framework-parent-reversion
 * @description: 系统用户权限业务逻辑封装
 * @author: wdw
 * @create: 2020-01-20 23:13
 **/
public interface ISysPermissionService  {
    /**
     * 获取角色数据权限
     *
     * @param userId 用户ID
     * @return 角色权限信息
     */
    Set<String> getRolePermission(Long userId);

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户ID
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(Long userId);
}
