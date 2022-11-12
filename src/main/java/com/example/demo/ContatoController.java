package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContatoController {
	
	private final ContatoRepository repository;
	
	
	ContatoController(ContatoRepository repository){
		this.repository = repository;
	}
	
	@GetMapping("/contatos")
	  List<Contato> all() {
	    return repository.findAll();
	  }
	
	 @GetMapping("/contatos/{id}")
	  Contato one(@PathVariable Long id) {
	    
	    return repository.findById(id)
	      .orElseThrow(() -> new ContatoNotFoundException(id));
	  }
	
	 @PostMapping("/contatos")
	  Contato newContato(@RequestBody Contato newContato) {
	    return repository.save(newContato);
	  }
	 
	 @PutMapping("/contatos/{id}")
	  Contato replaceContato(@RequestBody Contato newContato, @PathVariable Long id) {
	    
	    return repository.findById(id)
	      .map(Contato -> {
	        Contato.setNome(newContato.getNome());
	        Contato.setTelefone(newContato.getTelefone());
	        return repository.save(Contato);
	      })
	      .orElseGet(() -> {
	        newContato.setId(id);
	        return repository.save(newContato);
	      });
	  }
	 
	 @DeleteMapping("/contatos/{id}")
	  void deleteContato(@PathVariable Long id) {
	    repository.deleteById(id);
	  }
	
		
}
					