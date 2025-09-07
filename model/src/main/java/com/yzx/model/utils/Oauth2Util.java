package com.yzx.model.utils;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: xz-framework-parent
 * @description: oath2.0工具类
 * @author: Mr.Pan
 * @create: 2020-02-14 17:07
 */
public class Oauth2Util {
    public UserJwt getUserJwtFromHeader(HttpServletRequest request){
        Map jwtClaims = Oauth2Util.getJwtClaimsFromHeader(request);
        if(jwtClaims == null){
            return null;
        }
        Integer id = (Integer)jwtClaims.get("u_id");
        //Integer companyId = (Integer)jwtClaims.get("companyId")

        UserJwt userJwt = new UserJwt();
        userJwt.setId(id.longValue());
        userJwt.setUsername((String) jwtClaims.get("username"));
        //userJwt.setCompanyId(companyId.longValue())
        userJwt.setUtype((String) jwtClaims.get("utype"));
        userJwt.setAvatar((String) jwtClaims.get("avatar"));
        userJwt.setRole((String)jwtClaims.get("role"));
        return userJwt;
    }

    @Data
    public static class UserJwt{
        private Long id;
        private String username;
        private String avatar;
        private String utype;
        private String role;
    }

    public static Map getJwtClaimsFromHeader(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        //取出头信息
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization) || !authorization.contains("Bearer")) {
            return null;
        }
        //从Bearer 后边开始取出token
        String token = authorization.substring(7);
        Map map = null;
        try {
            //解析jwt
            Jwt decode = JwtHelper.decode(token);
            //得到 jwt中的用户信息
            String claims = decode.getClaims();
            //将jwt转为Map
            map = JSON.parseObject(claims, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean checkJwt(String token){
        try {
            JwtHelper.decode(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
