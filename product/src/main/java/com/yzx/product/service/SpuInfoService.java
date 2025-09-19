package com.yzx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzx.model.Result;
import com.yzx.model.product.SpuInfoEntity;

/**
 * @className: SpuInfoService
 * @author: yzx
 * @date: 2025/9/18 14:56
 * @Version: 1.0
 * @description:
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {
    Result up(String spuId);
}
