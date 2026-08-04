package com.omkar.inventory.common.security;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    // JWT Header
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    // Public Endpoints
    public static final String AUTH_API = "/api/auth/**";
    public static final String ACTUATOR_API = "/actuator/**";

    // Swagger / OpenAPI
    public static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**"
    };
}