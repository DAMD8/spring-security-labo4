package com.server.app.services;

import com.server.app.dto.auth.LoginRequest;
import com.server.app.dto.auth.AuthResponse;
import com.server.app.dto.auth.UpdatePasswordRequest;
import com.server.app.dto.user.UserCreateDto;
import com.server.app.dto.user.UserUpdateDto;
import com.server.app.entities.impl.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface UserService {
  AuthResponse login(LoginRequest request);
  AuthResponse signUp(UserCreateDto request);
  User updateProfile(Integer userId, UserUpdateDto request);
  void updatePassword(Integer userId, UpdatePasswordRequest request);
  User findById(Integer id);

  User create(@Valid UserCreateDto dto);

  Page<User> findAll(int page, int size, String search);

  User updateUser(int id, @Valid UserUpdateDto dto);
}