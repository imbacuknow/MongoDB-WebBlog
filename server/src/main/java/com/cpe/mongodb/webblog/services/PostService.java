package com.cpe.mongodb.webblog.services;

import com.cpe.mongodb.webblog.entity.Post;
import com.cpe.mongodb.webblog.entity.Tag;
import com.cpe.mongodb.webblog.entity.User;
import com.cpe.mongodb.webblog.model.PostModel;
import com.cpe.mongodb.webblog.repository.PostRepo;
import com.cpe.mongodb.webblog.repository.TagRepo;
import com.cpe.mongodb.webblog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private UserRepo userRepo;

    // retrieve upcoming page
    public List<Post> retrieveUpcomingPage() {
        return postRepo.findByVerified(false);
    }

    // retrieve home page
    public List<Post> retrieveHomePage() {
        return postRepo.findByVerified(true);
    }

    // retrieve content by id
    public ResponseEntity<Object> retrieveContentById(String id) {
        Optional<Post> post = postRepo.findById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok().body(post.get());
        } else {
            return ResponseEntity.badRequest().body("error: No ID is " + id);
        }
    }

    // create new post
    public ResponseEntity<Object> createNewPost(PostModel model) {
        Optional<User> user = userRepo.findById(model.getUser_id());
        Post post = new Post();
        post.setTitle(model.getTitle());
        post.setStory(model.getStory());
        post.setPostDate(model.getPostDate());
        post.setUsername(user.get().getUsername());
        post.setVerified(model.getVerified());

        List<Tag> listTags = new ArrayList<Tag>();
        for (int i = 0; i < model.getTags().length; i++) {
            Optional<Tag> tag = tagRepo.findByText(model.getTags()[i]);
            listTags.add(tag.get());
        }
        post.setTags(listTags);
        postRepo.insert(post);

        return ResponseEntity.ok("Post create Successfully.");
    }

    // this post is verified
    public ResponseEntity<Object> updateVerified(String id) {
        if (postRepo.findById(id).isPresent()) {
            Optional<Post> update_post = postRepo.findById(id).map(post -> {
                post.setVerified(true);
                return postRepo.save(post);
            });
            return ResponseEntity.ok("Post comfirm for read.");
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }

    // delete post
    public ResponseEntity<Object> deletePost(String id) {
        if (postRepo.findById(id).isPresent()) {
            postRepo.deleteById(id);
            if (postRepo.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(post) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(post) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
