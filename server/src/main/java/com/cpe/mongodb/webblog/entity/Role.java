package com.cpe.mongodb.webblog.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Document(collection = "role")
public class Role {
    @Id
    private String id;
    private String roleOfUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleOfUser() {
        return roleOfUser;
    }

    public void setRoleOfUser(String roleOfUser) {
        this.roleOfUser = roleOfUser;
    }
}
