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
@TableName("sys_role_dept")
public class SysRoleDept implements Serializable {
    /** 角色ID */
    private Long roleId;

    /** 部门ID */
    private Long deptId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId", getRoleId())
                .append("deptId", getDeptId())
                .toString();
    }
}
