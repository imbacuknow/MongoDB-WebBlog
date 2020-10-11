package com.cpe.mongodb.webblog.repository;

import com.cpe.mongodb.webblog.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends MongoRepository<Post, String> {
    List<Post> findByVerified(boolean verified);
}
