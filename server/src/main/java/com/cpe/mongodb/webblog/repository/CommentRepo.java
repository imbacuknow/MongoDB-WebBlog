package com.cpe.mongodb.webblog.repository;

import com.cpe.mongodb.webblog.entity.Comment;
import com.cpe.mongodb.webblog.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentRepo extends MongoRepository<Comment, String> {
    Collection<Comment> findByPost(Post post);
}
