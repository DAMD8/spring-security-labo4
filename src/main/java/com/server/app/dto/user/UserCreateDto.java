package com.server.app.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDto {
    @NotBlank(message = "Username es requerido")
    private String username;

    @NotBlank(message = "Password es requerido")
    private String password;

    @NotBlank(message = "Nombre es requerido")
    private String name;

    @NotBlank(message = "Apellido es requerido")
    private String surname;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email es requerido")
    private String email;
}