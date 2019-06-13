package com.ynu.makeup_you.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.makeup_you.entity.AdminUser;
import com.ynu.makeup_you.service.AdminUserService;
import com.ynu.makeup_you.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019/6/13 0013
 * BY hujianlong
 */

@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    TokenService tokenService;

    JSONObject jsonObject;

    // 管理员登录
    @PostMapping("/login")
    public Object login(AdminUser au){
        jsonObject = new JSONObject();
        AdminUser auBase = adminUserService.findAdminByName(au.getName());
        if (auBase == null){
            jsonObject.put("message","登录失败,此管理员不存在!");
            return jsonObject;
        }else{
            if (!auBase.getPassword().equals(au.getPassword())){
                jsonObject.put("message","登录失败,密码错误!");
                return jsonObject;
            }else{
                String token = tokenService.getToken(au);
                jsonObject.put("token",token);
                jsonObject.put("adminUser",au);
                return jsonObject;
            }
        }
    }
}
