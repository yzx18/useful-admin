package cn.poile.ucs.auth.service.serviceimpl;

import cn.poile.ucs.auth.mapper.BaseUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzx.model.ucenter.BaseUser;
import org.springframework.stereotype.Service;

/**
 * @className: BaseUserImpl
 * @author: yzx
 * @date: 2025/8/8 0:22
 * @Version: 1.0
 * @description:
 */
@Service
public class BaseUserImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {
}
