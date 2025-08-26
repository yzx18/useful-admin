package com.yzx.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.ucenter.BaseAuth;
import com.yzx.system.mapper.BaseAuthMapper;
import com.yzx.system.service.BaseAuthService;
import org.springframework.stereotype.Service;

/**
 * @className: BaseAuthServiceImpl
 * @author: yzx
 * @date: 2025/8/26 7:11
 * @Version: 1.0
 * @description:
 */
@Service
public class BaseAuthServiceImpl extends ServiceImpl<BaseAuthMapper, BaseAuth> implements BaseAuthService {
}
