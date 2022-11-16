package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ServerBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket; 


@Configuration
public class SwaggerConfig {

	@Bean
    public Docket publicApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                		.contact(new Contact("Vítor Viasdeski Monteiro", "www.linkedin.com/in/vitorviasdeskimonteiro\r\n"
                				+ "Luís Henrique Rodrigues", "www.linkedin.com/in/luis-henrique-retore-rodrigues\r\n"
                						+ ""))
                		.license("Apache License Version 2.0")
                		.licenseUrl("https://www.apache.org/licenses/")
                        .description("API REST para acesso ao contato de clientes.")
                        .title("API de Contatos")
                        .version("1.0.0")
                        .build())
                .host("clienteUI")
                .servers(new ServerBuilder()
                                .url("https://apirest-contatos-trabalhog2.herokuapp.com")
                                .build()
                                );
    }
}