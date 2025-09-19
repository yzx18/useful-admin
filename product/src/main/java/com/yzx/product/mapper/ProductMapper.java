package com.yzx.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzx.model.product.ProductAttrValueEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: ProductMapper
 * @author: yzx
 * @date: 2025/9/18 15:50
 * @Version: 1.0
 * @description:
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductAttrValueEntity> {
}
