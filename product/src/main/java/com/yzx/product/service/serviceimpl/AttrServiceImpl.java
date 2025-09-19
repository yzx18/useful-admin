package com.yzx.product.service.serviceimpl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.product.AttrEntity;
import com.yzx.product.mapper.AttrMapper;
import com.yzx.product.service.AttrService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @className: AttrServiceImpl
 * @author: yzx
 * @date: 2025/9/18 16:00
 * @Version: 1.0
 * @description:
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, AttrEntity> implements AttrService {
    @Override
    public List<Long> selectByIds(List<Long> attrIds) {
       return baseMapper.selectByids(attrIds);
    }
}
