package com.furandfeathers.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI furAndFeathersOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fur & Feathers API")
                        .description("Smart Pet Adoption & Care Network - REST API")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Fur & Feathers")
                                .email("support@furandfeathers.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repository")
                        .url("https://github.com/your-org/fur-and-feathers"));
    }
}
