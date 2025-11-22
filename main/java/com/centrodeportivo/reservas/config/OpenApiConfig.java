package com.centrodeportivo.reservas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI centroDeportivoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Centro Deportivo")
                        .version("1.0.0")
                        .description("Sistema de Gestión de Reservas y Recursos para Centro Deportivo"));
    }
}
