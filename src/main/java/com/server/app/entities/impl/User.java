package com.server.app.entities.impl;

import com.server.app.entities.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}