package cn.poile.ucs.auth.convert;

import cn.poile.ucs.auth.security.UserDetailsServiceImpl;
import cn.poile.ucs.auth.vo.UserDetailImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yzx
 * 自定义用户信息 oauth2默认是只有用户名 远程解系的时候可以直接拿来用
 */
@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        logger.debug("***********    jwt converter   ********************");
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        String name = authentication.getName();
        Object principal = authentication.getPrincipal();
        UserDetailImpl baseUserDetail = null;
        if (principal instanceof UserDetailImpl) {
            baseUserDetail = (UserDetailImpl) principal;
        } else {
            UserDetails user = userDetailsService.loadUserByUsername(name);
            baseUserDetail = (UserDetailImpl) user;
        }
        //TODO 此处根据用户的类别进行处理，让不同的用户携带不通的信息
        response.put("userName", baseUserDetail.getUsername());
        response.put("mobile", baseUserDetail.getMobile());
        response.put("id", baseUserDetail.getId());
        response.put("text", baseUserDetail.getTest());
        response.put("user_details", baseUserDetail);
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        return null;
    }
}

