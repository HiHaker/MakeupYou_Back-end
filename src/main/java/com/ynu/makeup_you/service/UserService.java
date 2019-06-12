package com.ynu.makeup_you.service;

import com.ynu.makeup_you.entity.User;

import java.util.List;

/**
 * Created on 2019/5/15
 * BY hujianlong
 */
public interface UserService {

    /**
     * 增加用户、删除用户、更新用户、查询全部用户、根据姓名查询用户、根据ID查询用户接口
     */
    public void addUser(User user);
    public void deleteUser(String id);
    public void updateUser(User user);
    public List<User> findAllUser();
    public User getUserByID(String id);
    public User getUserByName(String name);
}
