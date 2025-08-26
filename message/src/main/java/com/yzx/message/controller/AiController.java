package com.yzx.message.controller;

import com.yzx.message.service.QwenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: AiController
 * @author: yzx
 * @date: 2025/8/5 19:39
 * @Version: 1.0
 * @description:
 */
@RestController
public class AiController {
    @Autowired
    private QwenService qwenService;

    @GetMapping("/demo/{message}")
    public String demo(@PathVariable("message") String message) {
        return qwenService.chat(message);
    }
}
