package com.example.applet.controllers.dto;

import com.example.applet.entities.User;

public class WeixinLoginRequest {
    private String code;
    private User user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}