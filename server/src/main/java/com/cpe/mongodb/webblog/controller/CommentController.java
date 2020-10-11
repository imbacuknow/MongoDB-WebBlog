package com.cpe.mongodb.webblog.controller;

import com.cpe.mongodb.webblog.entity.Comment;
import com.cpe.mongodb.webblog.model.CommentModel;
import com.cpe.mongodb.webblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/webblog/comment/")
@CrossOrigin(origins = "http://localhost:8080")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("postId/{id}")
    public Collection<Comment> getAllCommentInPost(@PathVariable String id) {
        return commentService.retrieveCommentInPost(id);
    }

    @PostMapping("add_new")
    public ResponseEntity<Object> addCommentInPost(@RequestBody CommentModel model) {
        return commentService.addNewComment(model);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable String id) {
        return commentService.deleteComment(id);
    }
}
