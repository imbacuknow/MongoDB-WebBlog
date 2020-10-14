package com.cpe.mongodb.webblog.services;

import com.cpe.mongodb.webblog.entity.Post;
import com.cpe.mongodb.webblog.entity.Role;
import com.cpe.mongodb.webblog.entity.User;
import com.cpe.mongodb.webblog.model.UserModel;
import com.cpe.mongodb.webblog.repository.PostRepo;
import com.cpe.mongodb.webblog.repository.RoleRepo;
import com.cpe.mongodb.webblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PostRepo postRepo;

    // findbyid
    public ResponseEntity<Object> retriveById(String id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.badRequest().body("error: No ID is " + id);
        }
    }

    // confirm to login
    public ResponseEntity<Object> confirmUsernameAndLogin(String username, String password) {
        Optional<User> user = userRepo.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.badRequest().body("error: Invalid username or password");
        }
    }

    // create new user
    public ResponseEntity<Object> createNewUser(UserModel model) {
        if (userRepo.findByEmail(model.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User.");
        } else if (userRepo.findByUsername(model.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("The User is already Present, Failed to Create new User.");
        } else {
            User user = new User();
            user.setUsername(model.getUsername());
            user.setPassword(model.getPassword());
            user.setEmail(model.getEmail());
            user.setName(model.getName());
            user.setRegisterDate(model.getRegisterDate());
            user.setRoleName("Newbie");
            userRepo.insert(user);

            return ResponseEntity.ok("User is created.");
        }
    }

    // update role
    public ResponseEntity<Object> updateUserRole(String username, String role_id) {
        if (userRepo.findByUsername(username).isPresent()) {
            Optional<User> user = userRepo.findByUsername(username).map(user1 -> {
                Optional<Role> role = roleRepo.findById(role_id);
                user1.setRoleName(role.get().getRoleOfUser());
                return userRepo.save(user1);
            });
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }

        return ResponseEntity.ok("Now updated user is done!");
    }

    // delete user
    public ResponseEntity<Object> delete(String id) {
        if (userRepo.findById(id).isPresent()) {
            Optional<User> user = userRepo.findById(id);
            List<Post> posts = postRepo.findByUsername(user.get().getUsername());
            postRepo.deleteAll(posts);
            userRepo.deleteById(id);
            if (userRepo.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(user) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(user) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
