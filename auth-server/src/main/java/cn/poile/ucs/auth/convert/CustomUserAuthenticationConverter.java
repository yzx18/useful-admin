package cn.poile.ucs.auth.convert;

import cn.poile.ucs.auth.security.UserNameUserDetailService;
import com.yzx.model.ucenter.BaseUserDetail;
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
    UserNameUserDetailService userDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        logger.debug("***********    jwt converter   ********************");
        LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
        String name = authentication.getName();
        Object principal = authentication.getPrincipal();
        BaseUserDetail baseUserDetail = null;
        if (principal instanceof BaseUserDetail) {
            baseUserDetail = (BaseUserDetail) principal;
        } else {
            UserDetails user = userDetailsService.loadUserByUsername(name);
            baseUserDetail = (BaseUserDetail) user;
        }

        //TODO 此处根据用户的类别进行处理，让不同的用户携带不通的信息
        response.put("userName", baseUserDetail.getBaseAuth().getUserName());
        response.put("nickName", baseUserDetail.getBaseUser().getNickName());
        response.put("sex", baseUserDetail.getBaseUser().getSex());
        response.put("phone", baseUserDetail.getBaseAuth().getPhoneNumber());
        response.put("id", baseUserDetail.getBaseUser().getUserId());
        response.put("isServant", baseUserDetail.getBaseUser().getIsServant());
        response.put("avatar", baseUserDetail.getBaseUser().getAvatar());
        response.put("ID", baseUserDetail.getBaseUser().getIdNumber());
        response.put("permissions", baseUserDetail.getPermissions());
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

