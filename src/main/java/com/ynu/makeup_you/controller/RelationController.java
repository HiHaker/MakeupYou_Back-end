package com.ynu.makeup_you.controller;

import com.ynu.makeup_you.entity.Relation;
import com.ynu.makeup_you.entity.User;
import com.ynu.makeup_you.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2019/5/27 0027
 * BY hujianlong
 */

@RestController
@RequestMapping("/relation")
public class RelationController {
    @Autowired
    RelationService relationService;

    /**
     * 增加一条关注记录，删除一条关注记录，查询对应用户的所有粉丝，查询对应用户的所有关注
     */

    /**
     * 添加关注记录
     * @param fansID
     * @param followsID
     */
    @PostMapping("/addRelation")
    public void addRelation(@RequestParam("fansID") String fansID, @RequestParam("followsID") String followsID){
        Relation relation = new Relation(fansID,followsID);
        relationService.addFollow(relation);
    }

    /**
     * 删除关注记录
     * @param fansID
     * @param followsID
     */
    @DeleteMapping("/deleteRelation")
    public void deleteRelation(@RequestParam("fansID") String fansID, @RequestParam("followsID") String followsID){
        relationService.deleteFollow(fansID, followsID);
    }


    /**
     * 得到用户所有粉丝
     * @param uid
     * @return
     */
    @GetMapping("/findFans/{uid}")
    public List<User> getAllFans(@PathVariable("uid") String uid){
        List<User> fanslist = relationService.findFans(uid);
        return fanslist;
    }

    /**
     * 得到用户的全部关注
     * @param uid
     * @return
     */
    @GetMapping("/findFollows/{uid}")
    public List<User> getAllFollows(@PathVariable("uid") String uid){
        List<User> followslist = relationService.findFollows(uid);
        return followslist;
    }
}