package com.server.app.controllers;

import com.server.app.config.JsonWebToken;
import com.server.app.dto.auth.*;
import com.server.app.dto.user.UserCreateDto;
import com.server.app.dto.user.UserUpdateDto;
import com.server.app.entities.impl.User;
import com.server.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JsonWebToken jwtUtil;

    public AuthController(UserService userService, JsonWebToken jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody UserCreateDto request) {
        return ResponseEntity.ok(userService.signUp(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.findById(currentUser.getId()));
    }

    @PutMapping("/update/profile")
    public ResponseEntity<AuthResponse> updateProfile(@Valid @RequestBody UserUpdateDto request) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User updatedUser = userService.updateProfile(currentUser.getId(), request);
        return ResponseEntity.ok(new AuthResponse(jwtUtil.createToken(updatedUser), updatedUser));
    }

    @PutMapping("/update/password")
    public ResponseEntity<User> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updatePassword(currentUser.getId(), request);
        return ResponseEntity.ok(userService.findById(currentUser.getId()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}