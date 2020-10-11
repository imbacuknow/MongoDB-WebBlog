package com.cpe.mongodb.webblog.repository;

import com.cpe.mongodb.webblog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
