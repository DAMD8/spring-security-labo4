package com.server.app.dto.auth;

import com.server.app.entities.impl.User;

public class AuthResponse {
    private String token;
    private User data;

    public AuthResponse(String token, User data) {
        this.token = token;
        this.data = data;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public User getData() { return data; }
    public void setData(User data) { this.data = data; }
}