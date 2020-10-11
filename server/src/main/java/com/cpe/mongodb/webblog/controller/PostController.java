package com.cpe.mongodb.webblog.controller;

import com.cpe.mongodb.webblog.entity.Post;
import com.cpe.mongodb.webblog.model.PostModel;
import com.cpe.mongodb.webblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/webblog/post/")
@CrossOrigin(origins = "http://localhost:8080")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("home")
    public Collection<Post> getHomePage() {
        return postService.retrieveHomePage();
    }

    @GetMapping("upcoming")
    public Collection<Post> getUpcomingPage() {
        return postService.retrieveUpcomingPage();
    }

    @GetMapping("content/{id}")
    public ResponseEntity<Object> getPostId(@PathVariable String id) {
        return postService.retrieveContentById(id);
    }

    @PostMapping("create")
    public ResponseEntity<Object> createPost(@RequestBody PostModel model) {
        return postService.createNewPost(model);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable String id) {
        return postService.updateVerified(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable String id) {
        return postService.deletePost(id);
    }
}
