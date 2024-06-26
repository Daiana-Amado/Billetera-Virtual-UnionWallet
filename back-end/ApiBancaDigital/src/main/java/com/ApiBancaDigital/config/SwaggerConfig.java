package com.ApiBancaDigital.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Swagger - Banca Digital",
                version = "1.0.0",
                description = "Descripción de el API y los endpoint "
        )
)
public class SwaggerConfig {
}
