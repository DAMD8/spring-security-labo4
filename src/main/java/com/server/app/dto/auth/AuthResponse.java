package com.server.app.dto.auth;

import com.server.app.entities.impl.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    private String token;
    private User data;

    public AuthResponse(String token, User data) {
        this.token = token;
        this.data = data;
    }

}