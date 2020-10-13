package com.cpe.mongodb.webblog.model;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

public class CommentModel {
    @NotNull
    private String msg;
    private Date commentDate;
    @NotNull
    private String user_id;
    @NotNull
    private String post_id;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
