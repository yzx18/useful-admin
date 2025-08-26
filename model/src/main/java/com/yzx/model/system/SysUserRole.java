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
 * @create: 2020-01-22 21:48
 **/

@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("roleId", getRoleId())
                .toString();
    }
}
