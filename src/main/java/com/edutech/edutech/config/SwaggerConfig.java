package com.edutech.edutech.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI edutechOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Edutech")
                        .description("Documentación interactiva para la API REST del sistema educativo Edutech")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo Edutech")
                                .email("soporte@edutech.cl")));
    }
}
