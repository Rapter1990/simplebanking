package com.eteration.simplebanking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bankingApiOpenAPI() {
        Contact contact = new Contact()
                .name("Sercan Noyan Germiyanoğlu")
                .url("https://github.com/Rapter1990/simplebanking/");

        Info info = new Info()
                .title("Simple Banking App API")
                .version("1.0.0")
                .description("Case Study - Simple Banking App " +
                        "(Spring Boot, Gradle, JUnit, Integration Test, Gradle, Postgresql, Prometheus, Grafana, Github Actions, Postman)")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}
