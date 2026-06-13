package com.server.app.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePasswordRequest {
    @NotBlank(message = "La contraseña antigua es obligatoria")
    private String oldpassword;

    @NotBlank(message = "La nueva contraseña es obligatoria")
    private String newpassword;

    @NotBlank(message = "La confirmación es obligatoria")
    private String confirmpassword;

}