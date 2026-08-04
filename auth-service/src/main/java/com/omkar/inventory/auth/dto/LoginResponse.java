package com.omkar.inventory.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Login Response")
public class LoginResponse {
    @Schema(
            description = "JWT Access Token",
            example = "eyJhbGciOiJIUzI1NiJ9..."
    )
    private String token;

    private String tokenType;

}