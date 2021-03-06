package com.cpe.mongodb.webblog.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Comment {
    private String msg;
    private Date commentDate;
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
