package com.ynu.makeup_you.service;

import java.util.List;

/**
 * Created on 2019/5/29 0029
 * BY hujianlong
 */
public interface ImageService {
    /**
     * 根据帖子的id查找该帖子的所有图片
     * @param postID
     * @return
     */
    public List<String> getAllImg(String postID);
}
