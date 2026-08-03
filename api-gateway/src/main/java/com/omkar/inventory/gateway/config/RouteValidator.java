package com.omkar.inventory.gateway.config;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(

            "/api/auth/register",
            "/api/auth/login",

            "/v3/api-docs",
            "/v3/api-docs/",
            "/swagger-ui",
            "/swagger-ui/",

            "/swagger-ui.html",

            "/actuator",

            "/eureka"

    );

    public Predicate<String> isSecured =

            uri -> openApiEndpoints
                    .stream()
                    .noneMatch(uri::startsWith);

}