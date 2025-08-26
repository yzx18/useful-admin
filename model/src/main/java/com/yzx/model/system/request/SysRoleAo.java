package com.yzx.model.system.request;

import com.yzx.model.system.SysRole;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: xz-framework-parent-reversion
 * @description: 添加系统角色对象封装
 * @author: wdw
 * @create: 2020-02-03 16:13
 **/
@Data
@ToString
public class SysRoleAo extends SysRole implements Serializable {

    /** 菜单组 */
    private Long[] menuIds;

    /** 部门组（数据权限） */
    private Long[] deptIds;

}
