package com.cpe.mongodb.webblog.model;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

public class PostModel {
    @NotNull
    private String title;
    @NotNull
    private String story;
    private Date postDate;
    private boolean verified;
    @NotNull
    private String user_id;
    private String[] tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Date getPostDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
