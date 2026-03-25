package com.stellantis.operix.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email obligatoire")
    @Email
    private String email;
    @NotBlank(message = "Mot de passe obligatoire")
    private String password;
}