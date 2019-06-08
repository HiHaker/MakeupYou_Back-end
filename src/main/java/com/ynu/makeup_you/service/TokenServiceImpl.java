package com.ynu.makeup_you.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ynu.makeup_you.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created on 2019/6/8 0008
 * BY hujianlong
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {
        String token="";
        // 将 user id 保存到 token 里面, 以 password 作为 token 的密钥
        token= JWT.create().withAudience(user.getUid()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
