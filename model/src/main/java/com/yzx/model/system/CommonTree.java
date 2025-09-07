package com.yzx.model.system;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yzx.model.system.response.SysMenuDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommonTree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CommonTree> children;

    public CommonTree() {}

    public CommonTree(SysDept dept) {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        List<SysDept> children = dept.getChildren();
        if(children != null){
            this.children = children.stream().map(CommonTree::new).collect(Collectors.toList());
        }
    }

    public CommonTree(SysMenuTree menu) {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        List<SysMenuTree> children = menu.getChildren();
        if(children != null){
            this.children = children.stream().map(CommonTree::new).collect(Collectors.toList());
        }
    }

    public CommonTree(SysMenuDto menu) {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        List<SysMenuDto> children = menu.getChildren();
        if(children != null){
            this.children = children.stream().map(CommonTree::new).collect(Collectors.toList());
        }
    }
}
