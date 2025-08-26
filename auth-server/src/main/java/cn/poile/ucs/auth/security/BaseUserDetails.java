package cn.poile.ucs.auth.security;

import com.yzx.apiclient.api.SystemApi;
import com.yzx.model.ucenter.BaseAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @className: BaseUserDetails
 * @author: yzx
 * @date: 2025/8/21 6:24
 * @Version: 1.0
 * @description:
 */
@Slf4j
public abstract class BaseUserDetails implements UserDetailsService {

    @Autowired
    SystemApi systemApi;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return null;
    }

    protected abstract BaseAuth getBaseAuth(String username);
}
