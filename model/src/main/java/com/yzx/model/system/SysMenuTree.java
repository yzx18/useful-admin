package com.yzx.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: xz-framework-parent-reversion
 * @description: 前端菜单树状结构数据封装
 * @author: wdw
 * @create: 2020-01-22 11:13
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_menu")
@ToString(callSuper = true)
public class SysMenuTree extends SysMenu {

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 子菜单
     */
    private List<SysMenuTree> children = new ArrayList<SysMenuTree>();
}
