package com.yzx.model.system.request;

import lombok.Data;

/**
 * @program: xz-framework-parent-reversion
 * @description: 角色的条件查询条件封装
 * @author: wdw
 * @create: 2020-02-03 09:41
 **/
@Data
public class SysRoleQuery {

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色状态（0正常 1停用） */
    private String status;

    private Integer pageSize;

    private Integer pageNum;
}
