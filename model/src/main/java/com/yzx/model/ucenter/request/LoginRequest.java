package com.yzx.model.ucenter.request;

import lombok.Data;

/**
 * @program: xz-framework-parent-reversion
 * @description: 请求参数实体封装
 * @author: wdw
 * @create: 2020-02-08 12:58
 **/
@Data
public class LoginRequest {
    String mobile;
    String username;
    String password;
    String verifyCode;
    String uuid;
    String grantType;
}
