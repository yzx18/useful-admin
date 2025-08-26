package com.yzx.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: xz-framework-parent
 * @description: yongb
 * @author: qinBinQi
 * @create: 2020-03-09 11:17
 */
@Data
@TableName( "sys_user")
public class SysUserProp {

    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    private Integer distributionStatus;

    /**
     * 用户昵称
     */
    @NotNull
    private String nickName;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 是否为父节点
     */
    private Integer isParent;

    /**
     * 统计子节点个数
     */
    private Integer countChild;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户邮箱
     */
    @Email(message = "email格式错误")
    private String email;

    /**
     * 手机号码
     */
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{8}$",message = "手机号码格式错误")
    private String phoneNumber;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;

    /**
     * 生日
     */
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 身份证号码
     */
    private String idNumber;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 申请成为分销商时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date traderTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 团队id
     */
    private Integer groupId;


    /**
     * 部门表 sys_dept
     *
     * @author ruoyi
     */
    public static class SysDept extends BaseEntity
    {
        private static final long serialVersionUID = 1L;

        /** 部门ID */
        private Long deptId;

        /** 父部门ID */
        private Long parentId;

        /** 祖级列表 */
        private String ancestors;

        /** 部门名称 */
        private String deptName;

        /** 显示顺序 */
        private Integer orderNum;

        /** 负责人 */
        private String leader;

        /** 联系电话 */
        private String phone;

        /** 邮箱 */
        private String email;

        /** 部门状态:0正常,1停用 */
        private String status;

        /** 删除标志（0代表存在 2代表删除） */
        private String delFlag;

        /** 父部门名称 */
        private String parentName;

        /** 子部门 */
        private List<SysDept> children = new ArrayList<SysDept>();

        public Long getDeptId()
        {
            return deptId;
        }

        public void setDeptId(Long deptId)
        {
            this.deptId = deptId;
        }

        public Long getParentId()
        {
            return parentId;
        }

        public void setParentId(Long parentId)
        {
            this.parentId = parentId;
        }

        public String getAncestors()
        {
            return ancestors;
        }

        public void setAncestors(String ancestors)
        {
            this.ancestors = ancestors;
        }

        @NotBlank(message = "部门名称不能为空")
        @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
        public String getDeptName()
        {
            return deptName;
        }

        public void setDeptName(String deptName)
        {
            this.deptName = deptName;
        }

        @NotNull(message = "显示顺序不能为空")
        public Integer getOrderNum()
        {
            return orderNum;
        }

        public void setOrderNum(Integer orderNum)
        {
            this.orderNum = orderNum;
        }

        public String getLeader()
        {
            return leader;
        }

        public void setLeader(String leader)
        {
            this.leader = leader;
        }

        @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }

        @Email(message = "邮箱格式不正确")
        @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
        public String getEmail()
        {
            return email;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public String getStatus()
        {
            return status;
        }

        public void setStatus(String status)
        {
            this.status = status;
        }

        public String getDelFlag()
        {
            return delFlag;
        }

        public void setDelFlag(String delFlag)
        {
            this.delFlag = delFlag;
        }

        public String getParentName()
        {
            return parentName;
        }

        public void setParentName(String parentName)
        {
            this.parentName = parentName;
        }

        public List<SysDept> getChildren()
        {
            return children;
        }

        public void setChildren(List<SysDept> children)
        {
            this.children = children;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("deptName", getDeptName())
                .append("orderNum", getOrderNum())
                .append("leader", getLeader())
                .append("phone", getPhone())
                .append("email", getEmail())
                .append("status", getStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
        }
    }
}
