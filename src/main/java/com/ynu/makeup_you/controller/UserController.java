package com.ynu.makeup_you.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.makeup_you.annotation.UserLoginToken;
import com.ynu.makeup_you.entity.User;
import com.ynu.makeup_you.service.TokenService;
import com.ynu.makeup_you.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2019/5/15
 * BY hujianlong
 */

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 对用户的增删改查控制
     */

    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    /**
     * 增加一个用户
     * @param uid
     * @param username
     * @param password
     * @param birthday
     * @param sex
     * @param age
     * @param register_date
     * @param avatarID
     * @param description
     * @param mailbox
     * @param last_login_time
     */

    @PostMapping("/addUser")
    public void addUser(
            @RequestParam("uid") String uid,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("birthday") String birthday,
            @RequestParam("sex") Integer sex,
            @RequestParam("age") Integer age,
            @RequestParam("register_date") String register_date,
            @RequestParam("avatarID") String avatarID,
            @RequestParam("description") String description,
            @RequestParam("mailbox") String mailbox,
            @RequestParam("last_login_time") String last_login_time
    ){
        User user = new User(uid,username,password,birthday,sex,age,register_date,
                avatarID,description,mailbox,last_login_time);
        userService.addUser(user);
    }

    /**
     * 删除一个用户
     * @param id
     */

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }

    /**
     * 修改用户
     * @param uid
     * @param username
     * @param password
     * @param birthday
     * @param sex
     * @param age
     * @param register_date
     * @param avatarID
     * @param description
     * @param mailbox
     * @param last_login_time
     */

    @PutMapping("/updateUser")
    public void updateUser(
                           @RequestParam("uid") String uid,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("birthday") String birthday,
                           @RequestParam("sex") Integer sex,
                           @RequestParam("age") Integer age,
                           @RequestParam("register_date") String register_date,
                           @RequestParam("avatarID") String avatarID,
                           @RequestParam("description") String description,
                           @RequestParam("mailbox") String mailbox,
                           @RequestParam("last_login_time") String last_login_time){
        User user = new User(uid,username,password,birthday,sex,age,register_date,
                avatarID,description,mailbox,last_login_time);
        userService.addUser(user);
    }

    /**
     * 查找全部的用户
     * @return
     */

    @GetMapping("/findAll")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    /**
     * 查找特定id的用户
     * @param id
     * @return
     */
    @GetMapping("/findUserByID/{id}")
    public User findOne(@PathVariable("id") String id){
        return userService.getUserByID(id);
    }

    /**
     * 用户登录
     * @param uid
     * @param username
     * @param password
     * @param birthday
     * @param sex
     * @param age
     * @param register_date
     * @param avatarID
     * @param description
     * @param mailbox
     * @param last_login_time
     * @return
     */
    @PostMapping("/login")
    public Object login(
            @RequestParam("uid") String uid,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("birthday") String birthday,
            @RequestParam("sex") Integer sex,
            @RequestParam("age") Integer age,
            @RequestParam("register_date") String register_date,
            @RequestParam("avatarID") String avatarID,
            @RequestParam("description") String description,
            @RequestParam("mailbox") String mailbox,
            @RequestParam("last_login_time") String last_login_time
    ){
        User user = new User(uid,username,password,birthday,sex,age,register_date,
                avatarID,description,mailbox,last_login_time);
        JSONObject jsonObject = new JSONObject();
        User userBase = userService.getUserByID(user.getUid());
        if (userBase == null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else{
            if (!userBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误!");
                return jsonObject;
            }else{
                String token = tokenService.getToken(userBase);
                jsonObject.put("token",token);
                jsonObject.put("user",userBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已经通过验证";
    }

}
