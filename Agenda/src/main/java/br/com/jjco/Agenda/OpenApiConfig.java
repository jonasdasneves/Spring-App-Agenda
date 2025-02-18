package br.com.jjco.Agenda;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("Spring App Agenda")
                        .description("API de CRUD desenvolvida em Java e Spring para registro e manipulação de pessoas e seus respectivos contatos")
                        .contact(new Contact().name("Jonas").email("jonascamp2004@gmail.com").url("https://github.com/jonasdasneves"))
                        .version("Versão 1.0"));
    }
}
