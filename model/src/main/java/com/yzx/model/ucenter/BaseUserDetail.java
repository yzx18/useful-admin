package com.yzx.model.ucenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Set;

/**
 * @program: xz-framework-parent-reversion
 * @description: 包装org.springframework.security.core.userdetails.User类
 * @author: wdw
 * @create: 2020-01-21 13:45
 **/
public class BaseUserDetail implements UserDetails, CredentialsContainer {

    //基础的验证信息
    private final BaseAuth baseAuth;
    //用户的基本资料
    private final BaseUser baseUser;

    /** 权限列表  */
    private Set<String> permissions;

    private final org.springframework.security.core.userdetails.User user;

    public BaseUserDetail(BaseAuth baseAuth, BaseUser baseUser, User user) {
        this.baseAuth = baseAuth;
        this.baseUser = baseUser;
        this.user = user;
    }

    @Override
    public void eraseCredentials() {
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public BaseUser getBaseUser() {
        return baseUser;
    }

    public BaseAuth getBaseAuth() {
        return baseAuth;
    }

    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }
}
