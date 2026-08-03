package com.omkar.inventory.gateway.config;

import com.omkar.inventory.common.security.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http

                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                .authorizeExchange(exchange -> exchange

                        .pathMatchers(
                                SecurityConstants.AUTH_API,
                                SecurityConstants.ACTUATOR_API).permitAll()

                        .pathMatchers(
                                SecurityConstants.SWAGGER_WHITELIST
                        )
                        .permitAll()
                        .anyExchange().permitAll()

                )

                .build();
    }
}