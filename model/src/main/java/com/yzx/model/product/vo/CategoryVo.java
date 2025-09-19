package com.yzx.model.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @className: CategoryVo
 * @author: yzx
 * @date: 2025/9/18 11:52
 * @Version: 1.0
 * @description:
 */
@Data
public class CategoryVo {

    private Long catId;
    private String name;
    private String icon;
    private List<CategoryVo> children;
}
