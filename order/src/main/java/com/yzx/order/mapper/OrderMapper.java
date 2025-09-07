package com.yzx.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzx.model.order.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: OrderDao
 * @author: yzx
 * @date: 2025/8/30 9:14
 * @Version: 1.0
 * @description:
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

}
