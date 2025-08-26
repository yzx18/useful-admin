package com.yzx.model.system.response;

import lombok.Data;

/**
 * @program: xz-framework-parent
 * @description: 分销用户基本信息
 * @author: wdw
 * @create: 2020-07-17 10:26
 **/
@Data
public class CommissionUserBaseInfo {
    private Long userId;
    private String userName;
    private String avatar;
    private String referrerName;
    private Integer withdrawSuccess;
}
