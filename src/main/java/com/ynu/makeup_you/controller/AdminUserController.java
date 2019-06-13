package com.ynu.makeup_you.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.makeup_you.entity.AdminUser;
import com.ynu.makeup_you.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2019/6/13 0013
 * BY hujianlong
 */

@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    JSONObject jsonObject;

    // 增加管理员
    @PostMapping("/addAdminUser")
    public Object addAdminUser(AdminUser au){
        jsonObject = new JSONObject();
        if (adminUserService.findAdminByName(au.getName()) == null){
            adminUserService.addAdmin(au);
            jsonObject.put("adminUser",au);
        }else{
            jsonObject.put("message","创建管理员失败,管理员已经存在!");
        }
        return jsonObject;
    }

    // 删除管理员
    @DeleteMapping("/deleteAdminUser")
    public Object deleteAdminUser(@RequestParam("name") String name){
        jsonObject = new JSONObject();
        if (adminUserService.findAdminByName(name) == null){
            jsonObject.put("message","删除失败,管理员不存在!");
        }else{
            adminUserService.deleteAdmin(name);
            jsonObject.put("message","删除成功!");
        }
        return jsonObject;
    }

    // 更新管理员
    @PutMapping("/updateAdminUser")
    public Object updateAdminUser(AdminUser au){
        jsonObject = new JSONObject();
        if (adminUserService.findAdminByName(au.getName()) == null){
            jsonObject.put("message","更新失败,管理员不存在!");
        }else{
            adminUserService.updateAdmin(au);
            jsonObject.put("admin",au);
        }
        return jsonObject;
    }
}