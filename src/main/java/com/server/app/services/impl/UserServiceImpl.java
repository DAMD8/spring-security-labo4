package com.server.app.services.impl;

import com.server.app.config.JsonWebToken;
import com.server.app.dto.auth.LoginRequest;
import com.server.app.dto.auth.AuthResponse;
import com.server.app.dto.auth.UpdatePasswordRequest;
import com.server.app.dto.user.UserCreateDto;
import com.server.app.dto.user.UserUpdateDto;
import com.server.app.entities.Role;
import com.server.app.entities.impl.User;
import com.server.app.repositories.RoleRepository;
import com.server.app.repositories.UserRepository;
import com.server.app.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JsonWebToken jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, JsonWebToken jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        if (!user.getRole().getActive()) {
            throw new BadCredentialsException("Your account role is not active");
        }
        if (user.isBlocked()) {
            throw new BadCredentialsException("Your account has been blocked");
        }

        return new AuthResponse(jwtUtil.createToken(user), user);
    }

    @Override
    @Transactional
    public AuthResponse signUp(UserCreateDto request) {
        Role defaultRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new IllegalStateException("Error interno: Rol base no configurado"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(defaultRole);
        user.setBlocked(false);

        User savedUser = userRepository.save(user);
        return new AuthResponse(jwtUtil.createToken(savedUser), savedUser);
    }

    @Override
    @Transactional
    public User updateProfile(Integer userId, UserUpdateDto request) {
        User user = findById(userId);
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(Integer userId, UpdatePasswordRequest request) {
        User user = findById(userId);

        if (!passwordEncoder.matches(request.getOldpassword(), user.getPassword())) {
            throw new IllegalArgumentException("La contraseña anterior no coincide");
        }
        if (!request.getNewpassword().equals(request.getConfirmpassword())) {
            throw new IllegalArgumentException("La confirmación de la contraseña no coincide");
        }

        user.setPassword(passwordEncoder.encode(request.getNewpassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    @Override
    public User create(UserCreateDto dto) {
        return null;
    }

    @Override
    public Page<User> findAll(int page, int size, String search) {
        return null;
    }

    @Override
    public User updateUser(int id, UserUpdateDto dto) {
        return null;
    }
}