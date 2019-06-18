package com.ynu.makeup_you.controller;

import com.alibaba.fastjson.JSONObject;
import com.ynu.makeup_you.entity.Comments;
import com.ynu.makeup_you.entity.CommentsShow;
import com.ynu.makeup_you.entity.PostMessage;
import com.ynu.makeup_you.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/6/10 0010
 * BY hujianlong
 */
@CrossOrigin
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

    @GetMapping("/getMainPage")
    public List<Object> getMainPage(@RequestParam("userID") String uid){
        jsonObject = new JSONObject();
        List<Object> jsons = new ArrayList<>();
        // 查询所有的帖子信息
        List<PostMessage> postList = postMessageService.findAllPosts();
        for (PostMessage pm: postList){
            jsonObject.put("pid",pm.getPid());
            jsonObject.put("uid",pm.getUid());
            jsonObject.put("userHeadURL",userService.getUserByID(pm.getUid()).getAvatarID());
            jsonObject.put("userName",userService.getUserByID(pm.getUid()).getName());
            jsonObject.put("publishTime",pm.getPostTime());
            jsonObject.put("title",pm.getTitle());
            jsonObject.put("content",pm.getMessagebody());
            // 点赞
            jsonObject.put("likes",likesService.getAlluser(pm.getPid()).size());
            // 收藏
            jsonObject.put("favorites",favoritesService.getAlluser(pm.getPid()).size());
            // 当前用户是否点赞
            jsonObject.put("isLike",likesService.isLikedByMe(uid,pm.getPid()));
            // 当前用户是否收藏
            jsonObject.put("isCollection",favoritesService.isFavoritesByMe(uid,pm.getPid()));
            // 我是否关注了这个用户
            jsonObject.put("isAttent",relationService.isFollowed(uid,pm.getUid()));
            // 评论
            List<Comments> commentsList = commentsService.getAllCommentsOfPostmsg(pm.getPid());
            List<CommentsShow> commentsShowList = new ArrayList<>();
            for (Comments c:commentsList){
                CommentsShow commentsShow = new CommentsShow(c);
                commentsShow.setUserName(userService.getUserByID(c.getUserID()).getName());
                commentsShowList.add(commentsShow);
            }
            jsonObject.put("comments",commentsShowList);
            jsons.add(new JSONObject(jsonObject));
            jsonObject = new JSONObject();
        }
        return jsons;
    }

    @GetMapping("/getUserPage")
    public Object getUserPage(@RequestParam("userID") String uid){
        jsonObject = new JSONObject();
        jsonObject.put("userHeadURL",userService.getUserByID(uid).getAvatarID());
        jsonObject.put("userName",userService.getUserByID(uid).getName());
        jsonObject.put("description",userService.getUserByID(uid).getDescription());
        jsonObject.put("likes",likesService.getAllLikes(uid).size());
        jsonObject.put("favorites",favoritesService.getAllfavorites(uid).size());
        jsonObject.put("comments",favoritesService.getAllfavorites(uid));
        jsonObject.put("follows",relationService.findFollows(uid).size());
        jsonObject.put("fans",relationService.findFans(uid).size());
        return jsonObject;
    }
}