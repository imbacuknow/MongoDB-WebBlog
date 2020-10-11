package com.cpe.mongodb.webblog.controller;

import com.cpe.mongodb.webblog.entity.Tag;
import com.cpe.mongodb.webblog.model.TagModel;
import com.cpe.mongodb.webblog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/webblog/tag/")
@CrossOrigin(origins = "http://localhost:8080")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("showall")
    public Collection<Tag> getAllTag() {
        return tagService.retrieveAllTags();
    }

    @PostMapping("add")
    public ResponseEntity<Object> addTag(@RequestBody TagModel[] tag) {
        for (int i = 0; i < tag.length; i++) {
            tagService.addTag(tag[i]);
        }

        return ResponseEntity.ok().body("Tag is added.");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable String id) {
        return tagService.deleteTag(id);
    }
}
