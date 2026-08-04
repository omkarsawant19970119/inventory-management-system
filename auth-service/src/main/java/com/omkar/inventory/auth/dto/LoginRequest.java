package com.omkar.inventory.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Login Request")
public class LoginRequest {

    @Email
    @NotBlank
    @Schema(example = "admin")
    private String email;

    @NotBlank
    @Schema(example = "admin123")
    private String password;

}