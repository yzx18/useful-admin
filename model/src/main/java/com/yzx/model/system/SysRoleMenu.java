package com.yzx.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @program: xz-framework-parent-reversion
 * @description:
 * @author: wdw
 * @create: 2020-01-22 21:47
 **/

@Data
@TableName( "sys_role_menu")
public class SysRoleMenu implements Serializable {

    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId", getRoleId())
                .append("menuId", getMenuId())
                .toString();
    }
}
