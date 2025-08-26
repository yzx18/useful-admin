package com.yzx.model.system;

import com.yzx.model.ucenter.ext.SysUser;
import lombok.Data;

/**
 * @program: xz-framework-parent
 * @description: 分销商上三级信息
 * @author: wdw
 * @create: 2020-07-03 17:14
 **/
@Data
public class SuperAgent {

    /**
     * 第一个上级
     */
    SysUser level1;

    /**
     * 第二个上级
     */
    SysUser level2;

    /**
     * 第三个上级
     */
    SysUser level3;
}
