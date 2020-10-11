package com.cpe.mongodb.webblog.services;

import com.cpe.mongodb.webblog.entity.Tag;
import com.cpe.mongodb.webblog.model.TagModel;
import com.cpe.mongodb.webblog.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;

    // find all tags
    public List<Tag> retrieveAllTags() {
        return tagRepo.findAll();
    }

    // add new tag
    public ResponseEntity<Object> addTag(TagModel model) {
        Tag tag = new Tag();
        if (tagRepo.findByText(model.getText()).isPresent()) {
            return ResponseEntity.badRequest().body("The Tag(" + model.getText() + ") is already Present, Failed to Create new Tag.");
        } else {
            tag.setText(model.getText());
            tag.setColor(model.getColor());
            tagRepo.insert(tag);

            return ResponseEntity.ok("Tag is added.");
        }
    }

    // delete tag
    public ResponseEntity<Object> deleteTag(String id) {
        if (tagRepo.findById(id).isPresent()) {
            tagRepo.deleteById(id);
            if(tagRepo.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(tag) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(tag) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }
    }
}
