package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ContatoController {
	
	private final ContatoRepository repository;
	
	private final ContatoModelAssembler assembler;
	
	
	ContatoController(ContatoRepository repository, ContatoModelAssembler assembler){
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/contatos")
	CollectionModel<EntityModel<Contato>> all() {
	  List<EntityModel<Contato>> contatos = repository.findAll().stream()
	      .map(assembler::toModel)
	      .collect(Collectors.toList());

	  return CollectionModel.of(contatos, linkTo(methodOn(ContatoController.class).all()).withSelfRel());
	}

	
	@GetMapping("/contatos/{id}")
	EntityModel<Contato> one(@PathVariable Long id) {
	  Contato contato = repository.findById(id)
	      .orElseThrow(() -> new ContatoNotFoundException(id));

	  return assembler.toModel(contato);
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
					