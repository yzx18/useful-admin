package com.yzx.model.ucenter.ext;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yzx.model.system.SysDept;
import com.yzx.model.system.SysRole;
import com.yzx.model.ucenter.BaseUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @program: xz-framework-parent-reversion
 * @description: 系统用户的实体类封装
 * @author: wdw
 * @create: 2020-01-20 23:18
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class SysUser extends BaseUser {

    private String password;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 修改者
     */
    private String updateBy;

    /**
     * 部门对象
     */
    private SysDept dept;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 角色对象
     */
    private List<SysRole> roles;

    /**
     * 备注
     */
    private String remark;

    /**
     * 上级代理商的用户id
     */
    private Long agentId;

    /**
     * 是否是分销商，控制开关
     */
    private byte isAgent;

    /**
     * 成为分销商的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date agentTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
