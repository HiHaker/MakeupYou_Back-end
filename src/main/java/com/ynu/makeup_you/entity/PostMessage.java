package com.ynu.makeup_you.entity;

import javax.persistence.*;

/**
 * Created on 2019/5/15
 * BY hujianlong
 */

@Entity
@Table(name="postmessage")
public class PostMessage {
    @Id
    private String pid;
    @Column(name="post_time")
    private String post_time;
    @Column(name="type")
    private Integer type;
    @Column(name="title")
    private String title;
    @Column(name="messagebody")
    private String messagebody;

    /**
     * 构造函数
     */
    public PostMessage(){

    }

    public PostMessage(String pid, String postTime, Integer type, String title, String messagebody){
        this.pid = pid;
        this.post_time = postTime;
        this.type = type;
        this.title = title;
        this.messagebody = messagebody;
    }

    /**
     * Getter and Setter
     */
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPostTime() {
        return post_time;
    }

    public void setPostTime(String postTime) {
        this.post_time = postTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessagebody() {
        return messagebody;
    }

    public void setMessagebody(String messagebody) {
        this.messagebody = messagebody;
    }
}
