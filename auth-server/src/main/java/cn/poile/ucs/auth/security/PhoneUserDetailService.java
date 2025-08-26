package cn.poile.ucs.auth.security;

import com.yzx.model.ucenter.BaseAuth;

/**
 * @className: PhoneUserDetailService
 * @author: yzx
 * @date: 2025/8/26 7:53
 * @Version: 1.0
 * @description:
 */
public class PhoneUserDetailService  extends BaseUserDetails{
    @Override
    protected BaseAuth getBaseAuth(String username) {
        return null;
    }
}
