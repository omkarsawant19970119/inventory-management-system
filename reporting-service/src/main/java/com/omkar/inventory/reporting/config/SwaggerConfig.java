package com.omkar.inventory.reporting.config;

import com.omkar.inventory.common.swagger.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig extends SwaggerConfiguration {

    @Bean
    public OpenAPI authOpenAPI() {

        return buildOpenAPI(

                "Inventory Management System",

                "Reporting Service APIs"

        );

    }

}