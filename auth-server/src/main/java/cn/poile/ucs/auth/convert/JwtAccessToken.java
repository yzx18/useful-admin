package cn.poile.ucs.auth.convert;

import cn.poile.ucs.auth.security.UserDetailsServiceImpl;
import cn.poile.ucs.auth.vo.UserDetailImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fp295 on 2018/4/16.
 * 自定义JwtAccessToken转换器 这个类可以增强令牌
 */
@Component
@Slf4j
public class JwtAccessToken extends JwtAccessTokenConverter {


    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) oAuth2AccessToken;

        // 获取现有附加信息（而不是创建新的）
        Map<String, Object> map = token.getAdditionalInformation();
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        // 添加/更新自定义信息
        Object principal = oAuth2Authentication.getPrincipal();
        if (principal instanceof UserDetailImpl) {
            UserDetailImpl baseUserDetail = (UserDetailImpl) principal;
            map.put("username", baseUserDetail.getUsername());
            map.put("mobile", baseUserDetail.getMobile());
            map.put("mobile1", "1111");
        }
        token.setAdditionalInformation(map);
        return super.enhance(token, oAuth2Authentication);
    }

    /**
     * 解析token
     *
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        return oauth2AccessToken;
    }
}
