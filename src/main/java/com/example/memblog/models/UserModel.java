package com.example.memblog.models;

import com.example.memblog.entities.UserEntity;

public class UserModel {

    private Long id;
    private String username;

    public static UserModel toModel(UserEntity user){
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        return userModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
