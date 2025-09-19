package com.yzx.product.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.Result;
import com.yzx.model.StringUtils;
import com.yzx.model.product.ProductAttrValueEntity;
import com.yzx.model.product.SkuInfoEntity;
import com.yzx.model.product.SpuInfoEntity;
import com.yzx.product.service.AttrService;
import com.yzx.product.service.ProductAttrValueService;
import com.yzx.product.mapper.SpuMapper;
import com.yzx.product.service.SkuInfoService;
import com.yzx.product.service.SpuInfoService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @className: SpuInfoServiceImpl
 * @author: yzx
 * @date: 2025/9/18 15:02
 * @Version: 1.0
 * @description:
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuMapper, SpuInfoEntity> implements SpuInfoService {
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private AttrService attrService;
    @Override
    @Transactional
    public Result up(String spuId) {
        if (StringUtils.isEmpty(spuId))
            return Result.error("500", "商品参数为空");
        SkuInfoEntity one = skuInfoService.getOne(new LambdaQueryWrapper<SkuInfoEntity>().eq(SkuInfoEntity::getSpuId, spuId));
        List<ProductAttrValueEntity> list = productAttrValueService.list(new LambdaQueryWrapper<ProductAttrValueEntity>().eq(ProductAttrValueEntity::getSpuId, spuId));
        List<Long> AttrIds = list.stream().map(item -> item.getAttrId()).collect(Collectors.toList());
       List<Long> attrs = attrService.selectByIds(AttrIds);
        Set<Long> filterIds = attrs.stream().collect(Collectors.toSet());

        return null;
    }
}
