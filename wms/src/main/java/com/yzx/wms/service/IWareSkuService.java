package com.yzx.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzx.model.order.WareSkuLockVo;
import com.yzx.model.wms.WareSkuEntity;

/**
 * @className: WareSkuService
 * @author: yzx
 * @date: 2025/9/2 7:18
 * @Version: 1.0
 * @description:
 */
public interface IWareSkuService extends IService<WareSkuEntity> {
    boolean orderLockStock(WareSkuLockVo vo);
}
