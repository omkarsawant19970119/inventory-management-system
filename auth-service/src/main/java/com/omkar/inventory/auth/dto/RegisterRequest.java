package com.omkar.inventory.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "User Registration Request")
public class RegisterRequest {

    @NotBlank
    @Schema(example = "admin")
    private String firstName;

    @NotBlank

    private String lastName;

    @Email
    @Schema(example = "admin@test.com")
    private String email;

    @NotBlank
    @Schema(example = "admin123")
    private String password;

    private String phone;

}