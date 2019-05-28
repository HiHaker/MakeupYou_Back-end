package com.ynu.makeup_you.service;

import com.ynu.makeup_you.entity.Relation;
import com.ynu.makeup_you.entity.User;

import java.util.List;

/**
 * Created on 2019/5/27 0027
 * BY hujianlong
 */
public interface RelationService {
    /**
     * 增加一条关注记录，删除一条关注记录，查询对应用户的所有粉丝，查询对应用户的所有关注
     */
    public void addFollow(Relation relation);
    public void deleteFollow(String fansID, String followID);
    public List<User> findFans(String uid);
    public List<User> findFollows(String uid);
}
