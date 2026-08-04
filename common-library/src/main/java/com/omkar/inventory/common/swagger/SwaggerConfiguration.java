package com.omkar.inventory.common.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class SwaggerConfiguration {

    protected OpenAPI buildOpenAPI(
            String title,
            String description) {

        return new OpenAPI()

                .info(

                        new Info()

                                .title(title)

                                .description(description)

                                .version(SwaggerConstants.VERSION)

                                .contact(

                                        new Contact()

                                                .name(SwaggerConstants.CONTACT_NAME)

                                                .email(SwaggerConstants.CONTACT_EMAIL)

                                )

                                .license(

                                        new License()

                                                .name(SwaggerConstants.LICENSE_NAME)

                                )

                )

                .addSecurityItem(

                        new SecurityRequirement()

                                .addList(
                                        SwaggerConstants.SECURITY_SCHEME_NAME)

                )

                .components(

                        new Components()

                                .addSecuritySchemes(

                                        SwaggerConstants.SECURITY_SCHEME_NAME,

                                        new SecurityScheme()

                                                .type(SecurityScheme.Type.HTTP)

                                                .scheme(
                                                        SwaggerConstants.SECURITY_SCHEME)

                                                .bearerFormat(
                                                        SwaggerConstants.SECURITY_BEARER_FORMAT)

                                )

                );

    }

}