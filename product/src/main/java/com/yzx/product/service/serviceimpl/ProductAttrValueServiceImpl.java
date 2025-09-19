package com.yzx.product.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.product.ProductAttrValueEntity;
import com.yzx.product.service.ProductAttrValueService;
import com.yzx.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * @className: ProductAttrValueServiceImpl
 * @author: yzx
 * @date: 2025/9/18 15:49
 * @Version: 1.0
 * @description:
 */
@Service
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductMapper, ProductAttrValueEntity> implements ProductAttrValueService {
}
