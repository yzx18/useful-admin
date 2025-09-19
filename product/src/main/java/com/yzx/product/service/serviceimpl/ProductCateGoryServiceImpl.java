package com.yzx.product.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.Result;
import com.yzx.model.product.CategoryEntity;
import com.yzx.model.product.vo.CategoryVo;
import com.yzx.product.mapper.CategoryMapper;
import com.yzx.product.service.ProductCateGoryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: ProductCateGoryServiceImpl
 * @author: yzx
 * @date: 2025/9/18 11:47
 * @Version: 1.0
 * @description:
 */
@Service
public class ProductCateGoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements ProductCateGoryService {

    @Cacheable(value = "category",key = "#root.methodName")
    @Override
    public Result getCateGory() {
        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(null);
        return Result.success(buildCategoryTree(0L, categoryEntities));
    }

    private List<CategoryVo> buildCategoryTree(Long parentId, List<CategoryEntity> entities) {
        List<CategoryEntity> childCategories = entities.stream()
                .filter(category -> parentId.equals(category.getParentCid()))
                .collect(Collectors.toList());
        List<CategoryVo> categoryVos = new ArrayList<>();
        for (CategoryEntity entity : childCategories) {
            CategoryVo vo = new CategoryVo();
            vo.setCatId(entity.getCatId());
            vo.setIcon(entity.getIcon());
            vo.setName(entity.getName());
            vo.setChildren(buildCategoryTree(entity.getCatId(), entities));
            categoryVos.add(vo);
        }
        return categoryVos;
    }
}
