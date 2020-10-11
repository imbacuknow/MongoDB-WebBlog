package com.cpe.mongodb.webblog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "comment")
public class Comment {
    @Id
    private String id;
    private String msg;
    private Date commentDate;
    private User user;
    private Post post;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCommentDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
