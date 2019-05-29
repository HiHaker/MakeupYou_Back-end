package com.ynu.makeup_you.controller;

import com.ynu.makeup_you.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2019/5/29 0029
 * BY hujianlong
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    /**
     * 获得发帖的所有图片
     * @param postID
     * @return
     */
    @GetMapping("/getAllImg/{postID}")
    public List<String> getAllImg(@PathVariable("postID") String postID){
        return imageService.getAllImg(postID);
    }
}
