package com.ynu.makeup_you.controller;

import com.ynu.makeup_you.entity.Likes;
import com.ynu.makeup_you.entity.PostMessage;
import com.ynu.makeup_you.entity.User;
import com.ynu.makeup_you.repository.PostMessageRepository;
import com.ynu.makeup_you.repository.UserRepository;
import com.ynu.makeup_you.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/5/16
 * BY hujianlong
 */

@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    private LikesService likesService;

    @Autowired
    private PostMessageRepository postMessageRepository;

    @Autowired
    private UserRepository userRepository;

    // 得到某个用户所有点赞的帖子列表
    @GetMapping("/getLikesByUID/{userid}")
    public List<PostMessage> getFavorites(@PathVariable("userid") String id){
        List<Likes> likes_list = likesService.getAllLikes(id);
        List<PostMessage> post_list = new ArrayList<>();
        for (Likes l:likes_list){
            post_list.add(postMessageRepository.findById(l.getPostID()).orElse(null));
        }
        return post_list;
    }

    // 得到所有点赞此帖子的用户
    @GetMapping("/getUsersByPID/{postid}")
    public List<User> getUsers(@PathVariable("postid") String id){
        List<Likes> likes_list = likesService.getAlluser(id);
        List<User> user_list = new ArrayList<>();
        for (Likes l:likes_list){
            user_list.add(userRepository.findById(l.getUserID()).orElse(null));
        }
        return user_list;
    }

    // 增加点赞记录
    @PostMapping("/addRecord")
    public void addRecord(Likes likes){
        likesService.addRecord(likes);
    }

    // 删除一条点赞记录
    @DeleteMapping("/deleteRecord/{uid,pid}")
    public void deleteUser(@PathVariable("uid") String uid, @PathVariable("pid") String pid){
        likesService.deleteRecord(uid,pid);
    }
}
