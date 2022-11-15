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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/trabalhog2")
@CrossOrigin(origins = "*")
public class ContatoController {
	
	private final ContatoRepository repository;
	
	private final ContatoModelAssembler assembler;
	
	
	ContatoController(ContatoRepository repository, ContatoModelAssembler assembler){
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/contatos")
	@Operation(summary="Obtém todos os contatos cadastrados.")
	@ApiResponse(responseCode = "201", description = "Sucesso", content =  {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Contato.class))})
	CollectionModel<EntityModel<Contato>> all() {
	  List<EntityModel<Contato>> contatos = repository.findAll().stream()
	      .map(assembler::toModel)
	      .collect(Collectors.toList());

	  return CollectionModel.of(contatos, linkTo(methodOn(ContatoController.class).all()).withSelfRel());
	}
	


	
	@GetMapping("/contatos/{id}")
	@Operation(summary="Obtém um contato través do ID solicitado.")
	@ApiResponse(responseCode = "201", description = "Sucesso", content =  {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Contato.class))})
	EntityModel<Contato> one(@PathVariable Long id) {
	  Contato contato = repository.findById(id)
	      .orElseThrow(() -> new ContatoNotFoundException(id));

	  return assembler.toModel(contato);
	}
	
	
	 @PostMapping("/contatos")
	 @Operation(summary="Insere um novo contato.")
		@ApiResponse(responseCode = "201", description = "Sucesso", content =  {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Contato.class))})
	  Contato newContato(@RequestBody Contato newContato) {
	    return repository.save(newContato);
	  }
	 
	 @PutMapping("/contatos/{id}")
	 @Operation(summary="Atualiza um contato, aquele referente ao ID solicitado.")
		@ApiResponse(responseCode = "201", description = "Sucesso", content =  {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Contato.class))})
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
	 @Operation(summary="Deleta um contato, aquele referente ao ID solicitado.")
		@ApiResponse(responseCode = "201", description = "Sucesso", content =  {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Contato.class))})
	  void deleteContato(@PathVariable Long id) {
	    repository.deleteById(id);
	  }	
}
					