package com.yzx.model.system.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: xz-framework-parent-reversion
 * @description: 前端请求数据的封装
 * @author: wdw
 * @create: 2020-01-22 11:03
 **/
@Data
@Accessors(chain = true)
public class SysMenuQuery {
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单状态:0显示,1隐藏
     */
    private String visible;
}
