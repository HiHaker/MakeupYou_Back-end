package com.ynu.makeup_you.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.makeup_you.entity.PostMessage;
import com.ynu.makeup_you.repository.PostMessageRepository;
import com.ynu.makeup_you.service.PostMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created on 2019/5/16
 * BY hujianlong
 * 对帖子进行操作的Controller，用户进行发帖，删除贴子，更新帖子
 */
@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostMessageController {
    @Autowired
    private PostMessageService postMessageService;

    JSONObject jsonObject;

    // 用户发表帖子
    @PostMapping("/addRecord")
    public Object addPost(
                        @RequestParam("userID") String userID,
                        @RequestParam("type") Integer type,
                        @RequestParam("title") String title,
                        @RequestParam("messageBody") String messageBody){
        jsonObject = new JSONObject();
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String postTime = simpleDateFormat.format(date);
        // 生成一个id,UUID的变种
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0)
            hashCodeV=-hashCodeV;
        // 长度为10
        String pid = String.format("%010d",hashCodeV);
        PostMessage postMessage = new PostMessage(pid,userID,postTime,type,title,messageBody);
        postMessageService.addPost(postMessage);
        jsonObject.put("postMessage",postMessage);
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

    // 根据某一个特定的类型查询帖子
    @GetMapping("/findAllPostsByType/{type}")
    public List<PostMessage> findAllTypesPost(@PathVariable("type") Integer type){
        return postMessageService.findPostsByType(type);
    }

    // 根据用户的id查询其所发表的帖子
    @GetMapping("/findPostByUid/{userid}")
    public List<PostMessage> findPostByID(@PathVariable("userid") String id){
        return postMessageService.findPostsByUid(id);
    }

    // 查询出全部的帖子
    @GetMapping("/findAllPostMessages")
    public List<PostMessage> findAllPostMessages(){
        return postMessageService.findAllPosts();
    }

}