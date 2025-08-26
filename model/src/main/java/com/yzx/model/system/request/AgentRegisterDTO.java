package com.yzx.model.system.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: xz-framework-parent
 * @description: H5分销推广注册对象
 * @author: wdw
 * @create: 2020-07-02 10:09
 **/
@Data
public class AgentRegisterDTO {

    /**
     * 注册手机号
     */
    @NotBlank(message = "注册手机号不能为空")
    private String phoneNumber;

    /**
     * 注册密码
     */
    @NotBlank(message = "账号密码不能为空")
    private String pwd;

    /**
     * 短信验证码
     */
    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;

    /**
     * 推广人的用户id
     */
    @NotNull(message = "推广人的用户id不能为空")
    private Long userId;
}
