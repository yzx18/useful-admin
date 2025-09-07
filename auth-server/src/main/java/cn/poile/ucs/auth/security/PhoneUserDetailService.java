package cn.poile.ucs.auth.security;

import cn.poile.ucs.auth.mapper.BaseAuthMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yzx.model.ucenter.BaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: PhoneUserDetailService
 * @author: yzx
 * @date: 2025/8/26 7:53
 * @Version: 1.0
 * @description:
 */
@Component
public class PhoneUserDetailService extends BaseUserDetails {
    @Autowired
    private
    BaseAuthMapper baseAuthMapper;

    @Override
    protected BaseAuth getBaseAuth(String phone) {
        BaseAuth baseAuth = baseAuthMapper.selectOne(new LambdaQueryWrapper<BaseAuth>().eq(BaseAuth::getPhoneNumber, phone));
        return baseAuth;
    }
}
