package com.yzx.apiclient.api;

import com.yzx.model.order.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @className: CartFeignService
 * @author: yzx
 * @date: 2025/8/30 6:53
 * @Version: 1.0
 * @description:
 */
@FeignClient("cart-server")
public interface CartFeignService {
    /**
     * 查询当前用户购物车选中的商品项
     * @return
     */
    @GetMapping(value = "/cart/currentUserCartItems")
    List<OrderItemVo> getCurrentCartItems();
}
