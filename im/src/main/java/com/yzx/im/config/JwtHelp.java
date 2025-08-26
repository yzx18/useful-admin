package com.yzx.im.config;

import com.yzx.im.entity.SysUser;
import com.yzx.im.entity.UserDetailImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * @className: JWThelp
 * @author: yzx
 * @date: 2025/8/7 6:06
 * @Version: 1.0
 * @description:
 */
@Component
public class JwtHelp {
    /**
     * 获取当前登录的用户信息（SysUser）
     * @return 当前登录用户，未登录时返回null
     */
    public UserDetailImpl getCurrentUser() {
        // 1. 获取Spring Security的安全上下文（正确的包路径）
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return null; // 无安全上下文（未登录）
        }
        // 2. 从上下文获取认证信息（Authentication）
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            return null; // 未认证（未登录）
        }
        // 3. 获取用户主体（Principal），并转换为SysUser
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailImpl) {
            // 确认principal是SysUser类型（需SysUser实现UserDetails）
            return (UserDetailImpl) principal;
        } else {
            // 非预期类型（如匿名用户可能返回"anonymousUser"字符串）
            return null;
        }
    }
}
