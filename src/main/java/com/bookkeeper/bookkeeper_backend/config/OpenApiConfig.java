package com.bookkeeper.bookkeeper_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI bookKeeperOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("BookKeeper API")
                        .description("API for managing books, quotes, and tags")
                        .version("v1.0.0")
                        .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")));
    }
} 