package br.com.ana.desafioitau.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Itaú - Cadastro de Pessoas")
                        .version("1.0.0")
                        .description("""
                                API para cadastro de pessoas com:
                                
                                • Validação de CPF
                                • Integração ViaCEP
                                • Geração automática de login único
                                • Persistência PostgreSQL
                                • Docker Compose
                                """)
                        .contact(new Contact()
                                .name("Ana Silva")
                                .email("annabela.oliver@gmail.com")));
    }
}