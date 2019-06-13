package com.ynu.makeup_you.service;

import com.ynu.makeup_you.entity.AdminUser;

/**
 * Created on 2019/6/13 0013
 * BY hujianlong
 */
public interface AdminUserService {
    void addAdmin(AdminUser au);
    void deleteAdmin(String name);
    void updateAdmin(AdminUser au);
    AdminUser findAdminByName(String name);
}
