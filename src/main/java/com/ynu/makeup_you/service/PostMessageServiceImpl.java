package com.ynu.makeup_you.service;

import com.ynu.makeup_you.entity.PostMessage;
import com.ynu.makeup_you.repository.PostMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2019/5/16 0016
 * BY hujianlong
 */
@Service
public class PostMessageServiceImpl implements PostMessageService{

    @Autowired
    private PostMessageRepository postMessageRepository;

    /**
     * 发帖
     */
    @Override
    public void addPost(PostMessage postMessage) {
        postMessageRepository.save(postMessage);
    }

    /**
     * 删除帖
     */
    @Override
    public void deletePost(String postid) {
        postMessageRepository.deleteById(postid);
    }

    /**
     * 更新帖子
     */
    @Override
    public void updatePost(PostMessage postMessage) {
        postMessageRepository.save(postMessage);
    }

    /**
     * 根据用户id查询
     * @param uid
     * @return
     */
    @Override
    public List<PostMessage> findPostsByUid(String uid) {
        return postMessageRepository.findByUid(uid);
    }

    @Override
    public List<PostMessage> findPostsByType(Integer type) {
        return postMessageRepository.findByType(type);
    }

    @Override
    public List<PostMessage> findAllPosts() {
        return postMessageRepository.findAll();
    }
}
