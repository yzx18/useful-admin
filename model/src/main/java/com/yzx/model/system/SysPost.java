package com.yzx.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: xz-framework-parent-reversion
 * @description:
 * @author: wdw
 * @create: 2020-01-22 21:44
 **/
@Data
@TableName( "sys_post")
public class SysPost implements Serializable {
    /** 岗位序号 */
    private Long postId;

    /** 岗位编码 */
    @NotBlank(message = "岗位编码不能为空")
    @Size(min = 0, max = 64, message = "岗位编码长度不能超过64个字符")
    private String postCode;

    /** 岗位名称 */
    @NotBlank(message = "岗位名称不能为空")
    @Size(min = 0, max = 50, message = "岗位名称长度不能超过50个字符")
    private String postName;

    /** 岗位排序 */
    @NotBlank(message = "显示顺序不能为空")
    private String postSort;

    /** 状态（0正常 1停用） */
    private String status;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改者 */
    private String updateBy;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    /** 备注 */
    private String remark;

}
