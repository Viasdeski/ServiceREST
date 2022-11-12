package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	 
	  @Bean
	  CommandLineRunner initDatabase(ContatoRepository repository) {

	    return args -> {
	      log.info("Preloading " + repository.save(new Contato("Luis Henrique Rodrigues", "(54) 123456789")));
	      log.info("Preloading " + repository.save(new Contato("Vitor Viasdeski Monteiro", "(54) 987654321")));
	    };
	  }

}
