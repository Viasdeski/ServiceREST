package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "API REST de Contatos de Clientes - Trabalho G2 - POOA", version = "1.0.0",
        termsOfService = "",
        contact = @Contact(name = "Desenvolvido por: Vítor Viadeski Monteiro e Luís Henrique Rodrigues",
                           url = "https://github.com/Viasdeski/ServiceREST.git",
                           email = ""
                           ),
        license = @License(name = "Apache 2.0",
                           url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                           )),
        servers =
                {
                        @Server(url = "https://apirest-contatos-trabalhog2.herokuapp.com")
                }
)
@Slf4j
public class TrabalhoG2RestApplication {

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoG2RestApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://cliente-ui"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", ACCESS_CONTROL_ALLOW_ORIGIN, "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN, "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
