package com.ynu.makeup_you.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.makeup_you.entity.PostMessage;
import com.ynu.makeup_you.entity.Posts;
import com.ynu.makeup_you.repository.PostMessageRepository;
import com.ynu.makeup_you.service.PostMessageService;
import com.ynu.makeup_you.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 2019/5/16
 * BY hujianlong
 * 对帖子进行操作的Controller，用户进行发帖，删除贴子，更新帖子
 */

@RestController
@RequestMapping("/post")
public class PostMessageController {
    @Autowired
    private PostMessageService postMessageService;
    @Autowired
    private PostMessageRepository postMessageRepository;
    @Autowired
    private PostsService postsService;

    JSONObject jsonObject;

    // 用户发表帖子
    @PostMapping("/addRecord")
    public Object addPost(
                        @RequestParam("userID") String userID,
                        @RequestParam("postTime") String postTime,
                        @RequestParam("type") Integer type,
                        @RequestParam("title") String title,
                        @RequestParam("messageBody") String messageBody){
        jsonObject = new JSONObject();
        // 生成一个id,UUID的变种
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0)
            hashCodeV=-hashCodeV;
        // 长度为10
        String pid = String.format("%010d",hashCodeV);
        PostMessage postMessage = new PostMessage(pid,postTime,type,title,messageBody);
        Posts posts = new Posts(userID,pid,postTime);
        postMessageService.addPost(postMessage,posts);
        jsonObject.put("postMessage",postMessage);
        jsonObject.put("posts",posts);
        return jsonObject;
    }

    // 删除帖子
    @DeleteMapping("/deleteRecord/{postID}")
    public void deletePost(@PathVariable("postID") String postID){
        jsonObject = new JSONObject();
        postMessageService.deletePost(postID);
        jsonObject.put("message","删除成功!");
    }

    // 用户更新帖子
    @PutMapping("/updateRecord")
    public void updatePost(PostMessage postMessage){
        jsonObject = new JSONObject();
        postMessageService.updatePost(postMessage);
        jsonObject.put("message","更新成功!");
    }

    // 根据用户id查询出其发表的帖子
    @GetMapping("/findPostsByUID/{uid}")
    public List<PostMessage> findPostsByUid(@PathVariable("uid") String uid){
        List<Posts> posts_list = postsService.getAllPosts(uid);
        List<PostMessage> postMsg_list = new ArrayList<>();
        for (Posts p:posts_list){
            postMsg_list.add(postMessageRepository.findById(p.getPostID()).orElse(null));
        }
        return postMsg_list;
    }

    // 根据某一个特定的类型查询帖子
    @GetMapping("/findAllPostsByType/{type}")
    public List<PostMessage> findAllTypesPost(@PathVariable("type") Integer type){
        return postMessageService.findTypesPost(type);
    }

    // 根据帖子的id查询帖子
    @GetMapping("/findPostByID/{postid}")
    public PostMessage findPostByID(@PathVariable("postid") String id){
        return postMessageService.findPost(id);
    }

    // 查询出全部的帖子
    @GetMapping("/findAllPostMessages")
    public List<PostMessage> findAllPostMessages(){
        return postMessageService.findAllPost();
    }

}
