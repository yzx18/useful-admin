package com.yzx.model.system.response;

import lombok.Data;

/**
 * @program: xz-framework-parent
 * @description: 用于分销传出的数据封装
 * @author: wdw
 * @create: 2020-07-17 08:55
 **/
@Data
public class CommissionUser {

    private Long userId;

    private String userName;

    private Integer agentNum;

    private String avatar;

    private String createTime;
}
