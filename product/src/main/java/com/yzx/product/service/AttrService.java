package com.yzx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzx.model.product.AttrEntity;

import java.util.List;

/**
 * @className: AttrService
 * @author: yzx
 * @date: 2025/9/18 16:00
 * @Version: 1.0
 * @description:
 */
public interface AttrService extends IService<AttrEntity> {
    List<Long> selectByIds(List<Long> attrIds);
}
