package com.yzx.model.system;

import lombok.Data;

/**
 * @program: xz-framework-parent
 * @description: 分销商的所有信息
 * @author: wdw
 * @create: 2020-07-03 15:21
 **/

@Data
public class AgentUserInfoVo {
    /**
     * 一级下线的id
     */
    Long[] level1AgentIds;

    /**
     * 二级下线的id
     */
    Long[] level2AgentIds;

    /**
     * 三级下线的id
     */
    Long[] level3AgentIds;

}
