package com.cpe.mongodb.webblog.model;

import com.cpe.mongodb.webblog.entity.Role;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

public class UserModel {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String name;
    private Date registerDate;
    @NotNull
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterDate() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
