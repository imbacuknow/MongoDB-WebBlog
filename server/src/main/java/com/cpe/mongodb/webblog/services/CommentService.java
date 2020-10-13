package com.cpe.mongodb.webblog.services;

import com.cpe.mongodb.webblog.entity.Comment;
import com.cpe.mongodb.webblog.entity.Post;
import com.cpe.mongodb.webblog.entity.User;
import com.cpe.mongodb.webblog.model.CommentModel;
import com.cpe.mongodb.webblog.repository.CommentRepo;
import com.cpe.mongodb.webblog.repository.PostRepo;
import com.cpe.mongodb.webblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

//    // find by postid
//    public Collection<Comment> retrieveCommentInPost(@PathVariable String id) {
//        Optional<Post> post = postRepo.findById(id);
//        return commentRepo.findByPost(post.get());
//    }

//    // add comment to post
//    public ResponseEntity<Object> addNewComment(CommentModel model) {
//        Comment comment = new Comment();
//        Optional<User> user = userRepo.findById(model.getUser_id());
//        Optional<Post> post = postRepo.findById(model.getPost_id());
//        comment.setMsg(model.getMsg());
//        comment.setCommentDate(model.getCommentDate());
//        comment.setUser(user.get());
//        comment.setPost(post.get());
//        commentRepo.insert(comment);
//
//        return ResponseEntity.ok("Comment is added.");
//    }

    // add comment to post
    public ResponseEntity<Object> addNewComment(CommentModel model) {
        Comment comment = new Comment();
        Optional<User> user = userRepo.findById(model.getUser_id());
        Optional<Post> post = postRepo.findById(model.getPost_id());

        comment.setMsg(model.getMsg());
        comment.setCommentDate(model.getCommentDate());
        comment.setUsername(user.get().getUsername());

        List<Comment> listOfComments = new ArrayList<Comment>();
        listOfComments = post.get().getComments();
        listOfComments.add(comment);

        post.get().setComments(listOfComments);
        postRepo.save(post.get());

        return ResponseEntity.ok("Comment is added.");
    }

    // delete comment
    public ResponseEntity<Object> deleteComment(String id) {
        if (commentRepo.findById(id).isPresent()) {
            commentRepo.deleteById(id);
            if(commentRepo.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(comment) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(comment) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
