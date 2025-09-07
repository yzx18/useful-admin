package com.yzx.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.order.OrderItemEntity;
import com.yzx.order.mapper.OrderItemMapper;
import com.yzx.order.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * @className: OrderItemMapperServiceImpl
 * @author: yzx
 * @date: 2025/8/30 9:22
 * @Version: 1.0
 * @description:
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {
}
