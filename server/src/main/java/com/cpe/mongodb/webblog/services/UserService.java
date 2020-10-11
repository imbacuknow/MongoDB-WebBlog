package com.cpe.mongodb.webblog.services;

import com.cpe.mongodb.webblog.entity.User;
import com.cpe.mongodb.webblog.model.UserModel;
import com.cpe.mongodb.webblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    // findbyid
    public ResponseEntity<Object> retriveById(String id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.badRequest().body("error: No ID is " + id);
        }
    }

    // verify
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
            user.setRole(model.getRole());
            userRepo.insert(user);

            return ResponseEntity.ok("User is created.");
        }
    }

    // delete user
    public ResponseEntity<Object> delete(String id) {
        if (userRepo.findById(id).isPresent()) {
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
