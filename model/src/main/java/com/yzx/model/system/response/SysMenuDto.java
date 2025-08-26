package com.yzx.model.system.response;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yzx.model.system.SysMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: xz-framework-parent-reversion
 * @description: 菜单管理树状结构数据封装
 * @author: wdw
 * @create: 2020-02-03 20:41
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_menu")
@ToString(callSuper = true)
public class SysMenuDto extends SysMenu {
    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 子菜单
     */
    private List<SysMenuDto> children = new ArrayList<SysMenuDto>();

}
