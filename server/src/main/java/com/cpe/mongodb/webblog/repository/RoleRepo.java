package com.cpe.mongodb.webblog.repository;

import com.cpe.mongodb.webblog.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends MongoRepository<Role, String> {
    Optional<Role> findByRoleOfUser(String roleOfUser);
}
