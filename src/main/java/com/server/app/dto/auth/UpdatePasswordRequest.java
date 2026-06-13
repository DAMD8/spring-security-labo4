package com.server.app.dto.auth;

import jakarta.validation.constraints.NotBlank;

public class UpdatePasswordRequest {
    @NotBlank(message = "La contraseña antigua es obligatoria")
    private String oldpassword;

    @NotBlank(message = "La nueva contraseña es obligatoria")
    private String newpassword;

    @NotBlank(message = "La confirmación es obligatoria")
    private String confirmpassword;

    public String getOldpassword() { return oldpassword; }
    public void setOldpassword(String oldpassword) { this.oldpassword = oldpassword; }
    public String getNewpassword() { return newpassword; }
    public void setNewpassword(String newpassword) { this.newpassword = newpassword; }
    public String getConfirmpassword() { return confirmpassword; }
    public void setConfirmpassword(String confirmpassword) { this.confirmpassword = confirmpassword; }
}