package com.yzx.model.system.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: xz-framework-parent
 * @description: 二级分销推荐用户注册
 * @author: Mr.Pan
 * @create: 2020-05-09 15:16
 */
@Data
public class RegistryDto implements Serializable {

    private String phone;

    private String pwd;

    private String verify;

    private Long userId;
}
