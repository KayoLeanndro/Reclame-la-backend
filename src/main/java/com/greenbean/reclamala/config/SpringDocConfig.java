package com.greenbean.reclamala.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
            .title("API de Feedbacks")
            .version("1.0")
            .description("Documentação da API para gerenciamento de feedbacks de alunos"));
    }
}
