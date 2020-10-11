package com.cpe.mongodb.webblog.repository;

import com.cpe.mongodb.webblog.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepo extends MongoRepository<Tag, String> {
    Optional<Tag> findByText(String text);
}
