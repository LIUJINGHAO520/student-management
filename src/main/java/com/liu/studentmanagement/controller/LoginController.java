package com.liu.studentmanagement.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // 必须加，否则前端连不上
@RequestMapping("api")
public class LoginController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Map<String, Object> result = new HashMap<>();

        // 简单验证：账号 admin，密码 123456
        if ("admin".equals(username) && "123456".equals(password)) {
            result.put("code", 200);
            result.put("message", "登录成功");
            result.put("token", "admin-token-2026"); // 模拟一个Token
        } else {
            result.put("code", 400);
            result.put("message", "账号或密码错误");
        }
        return result;
    }
}