package com.cpe.mongodb.webblog.model;

import javax.validation.constraints.NotNull;

public class RoleModel {
    @NotNull
    private String roleOfUser;

    public String getRoleOfUser() {
        return roleOfUser;
    }

    public void setRoleOfUser(String roleOfUser) {
        this.roleOfUser = roleOfUser;
    }
}
