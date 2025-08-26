package com.yzx.model.system.response;

import lombok.Data;

/**
 * @program: xz-framework-parent
 * @description: 分销商的所有信息
 * @author: wdw
 * @create: 2020-07-03 15:21
 **/

@Data
public class CommissionUserInfoVo {
    /**
     * 一级下线的id
     */
    CommissionUser[] level1Agents;

    /**
     * 二级下线的id
     */
    CommissionUser[] level2Agents;

    /**
     * 三级下线的id
     */
    CommissionUser[] level3Agents;

}
