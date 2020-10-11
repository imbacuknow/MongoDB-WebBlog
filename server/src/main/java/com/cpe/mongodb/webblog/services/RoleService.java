package com.cpe.mongodb.webblog.services;

import com.cpe.mongodb.webblog.entity.Role;
import com.cpe.mongodb.webblog.model.RoleModel;
import com.cpe.mongodb.webblog.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    // get every roll
    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    // findbyid
    public ResponseEntity<Object> retrieveById(@PathVariable String id) {
        Optional<Role> role = roleRepo.findById(id);
        if(role.isPresent()) {
            return ResponseEntity.ok().body(role.get());
        } else {
            return ResponseEntity.badRequest().body("error: No ID is    " + id);
        }
    }

    // create new role
    public ResponseEntity<Object> createRole(RoleModel model) {
        if (roleRepo.findByRoleOfUser(model.getRoleOfUser()).isPresent()) {
            return ResponseEntity.badRequest().body("The RoleOfUser is already Present, Failed to Create new Role.");
        } else {
            Role role = new Role();
            role.setRoleOfUser(model.getRoleOfUser());
            roleRepo.insert(role);

            return ResponseEntity.ok("Role is created.");
        }
    }

    // delete role
    public ResponseEntity<Object> delete(String id) {
        if (roleRepo.findById(id).isPresent()) {
            roleRepo.deleteById(id);
            if (roleRepo.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete(role) the specified record.");
            } else {
                return ResponseEntity.ok().body("Successfully deleted(role) specified record.");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("No Records Found.");
        }

    }
}
