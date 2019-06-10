package com.ynu.makeup_you.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.makeup_you.entity.PostMessage;
import com.ynu.makeup_you.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2019/6/10 0010
 * BY hujianlong
 */
@RestController
@RequestMapping("/wxappservice")
public class WXController {
    @Autowired
    UserService userService;
    @Autowired
    PostMessageService postMessageService;
    @Autowired
    FavoritesService favoritesService;
    @Autowired
    LikesService likesService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    RelationService relationService;

    JSONObject jsonObject;
    JSONObject messages;

    @GetMapping("/getMainPage")
    public Object getMainPage(){
        jsonObject = new JSONObject();
        messages = new JSONObject();
        // 查询所有的帖子信息
        List<PostMessage> postList = postMessageService.findAllPosts();
        for (PostMessage pm: postList){
            jsonObject.put("userHeadURL",userService.getUserByID(pm.getUid()).getAvatarID());
            jsonObject.put("userName",userService.getUserByID(pm.getUid()).getName());
            jsonObject.put("postMessage",pm);
            // 点赞
            jsonObject.put("likes",likesService.getAlluser(pm.getPid()));
            // 收藏
            jsonObject.put("favorites",favoritesService.getAlluser(pm.getPid()));
            // 评论
            jsonObject.put("comments",commentsService.getAllCommentsOfPostmsg(pm.getPid()));
            messages.put("",jsonObject);
        }
        return messages;
    }

    @GetMapping("/getUserPage/{userID}")
    public Object getUserPage(@PathVariable("userID") String uid){
        jsonObject = new JSONObject();
        jsonObject.put("userHeadURL",userService.getUserByID(uid).getAvatarID());
        jsonObject.put("userName",userService.getUserByID(uid).getName());
        jsonObject.put("description",userService.getUserByID(uid).getDescription());
        jsonObject.put("likes",likesService.getAllLikes(uid));
        jsonObject.put("favorites",favoritesService.getAllfavorites(uid));
        jsonObject.put("comments",favoritesService.getAllfavorites(uid));
        jsonObject.put("follows",relationService.findFollows(uid));
        jsonObject.put("fans",relationService.findFans(uid));
        return jsonObject;
    }
}