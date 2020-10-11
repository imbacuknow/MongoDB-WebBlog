package com.cpe.mongodb.webblog.controller;

import com.cpe.mongodb.webblog.model.UserModel;
import com.cpe.mongodb.webblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webblog/user/")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("details/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        return userService.retriveById(id);
    }

    @GetMapping("login/{username}/{password}")
    public ResponseEntity<Object> loginToWeb(@PathVariable String username, @PathVariable String password) {
        return userService.confirmUsernameAndLogin(username, password);
    }

    @PostMapping("create")
    public ResponseEntity<Object> createUser(@RequestBody UserModel model) {
        return userService.createNewUser(model);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return userService.delete(id);
    }
}
