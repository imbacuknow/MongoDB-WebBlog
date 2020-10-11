package com.cpe.mongodb.webblog.controller;

import com.cpe.mongodb.webblog.entity.Role;
import com.cpe.mongodb.webblog.model.RoleModel;
import com.cpe.mongodb.webblog.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/webblog/role/")
@CrossOrigin(origins = "http://localhost:8080")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("details/all")
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping("details/{id}")
    public ResponseEntity<Object> getARole(@PathVariable String id) {
        return roleService.retrieveById(id);
    }

    @PostMapping("create")
    public ResponseEntity<Object> createRole(@RequestBody RoleModel model) {
        return roleService.createRole(model);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return roleService.delete(id);
    }
}

