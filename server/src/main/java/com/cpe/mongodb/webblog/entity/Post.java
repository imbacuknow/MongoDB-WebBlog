package com.cpe.mongodb.webblog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private String title;
    private String story;
    private Date postDate;
    private boolean verified;
    private String username;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Tag> tags;

    public String getId() {
        return id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
