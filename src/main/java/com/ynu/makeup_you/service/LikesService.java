package com.ynu.makeup_you.service;

import com.ynu.makeup_you.entity.Likes;

import java.util.List;

/**
 * Created on 2019/5/16
 * BY hujianlong
 */

public interface LikesService {
    /**
     * 增加一个点赞记录,删除一个点赞记录,根据用户id查询postid,根据postid查询用户id
     */
    public void addRecord(Likes likes);
    public void deleteRecord(String userID, String postID);

    public List<Likes> getAllLikes(String userID);
    public List<Likes> getAlluser(String postID);
}