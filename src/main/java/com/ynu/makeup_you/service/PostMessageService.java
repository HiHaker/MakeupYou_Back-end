package com.ynu.makeup_you.service;

import com.ynu.makeup_you.entity.PostMessage;

import java.util.List;

/**
 * Created on 2019/5/16 0016
 * BY hujianlong
 */
public interface PostMessageService {
    /**
     * 发帖、删除帖子、编辑帖子、根据用户id查询帖子，根据类型查询帖子，查询全部的帖子
     */
    public void addPost(PostMessage postMessage);
    public void deletePost(String postid);
    public void updatePost(PostMessage postMessage);
    public List<PostMessage> findPostsByUid(String uid);
    public List<PostMessage> findPostsByType(Integer type);
    public List<PostMessage> findAllPosts();
}
