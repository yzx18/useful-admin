package com.yzx.im.controller;

import com.yzx.im.config.JwtHelp;
import com.yzx.im.entity.SysUser;
import com.yzx.im.entity.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: Demo
 * @author: yzx
 * @date: 2025/8/6 15:19
 * @Version: 1.0
 * @description:
 */
@RestController
public class Demo {
    @Autowired
    private JwtHelp jwThelp;

    @PostMapping("demo")
    public UserDetailImpl demo() {
        return jwThelp.getCurrentUser();
    }
}
