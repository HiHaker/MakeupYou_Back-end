package com.ynu.makeup_you.repository;

import com.ynu.makeup_you.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2019/6/13 0013
 * BY hujianlong
 */
public interface AdminUserRepository extends JpaRepository<AdminUser,String> {
}
