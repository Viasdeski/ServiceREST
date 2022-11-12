package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ContatoModelAssembler implements RepresentationModelAssembler<Contato, EntityModel<Contato>> {

  @Override
  public EntityModel<Contato> toModel(Contato Contato) {

    return EntityModel.of(Contato, //
        linkTo(methodOn(ContatoController.class).one(Contato.getId())).withSelfRel(),
        linkTo(methodOn(ContatoController.class).all()).withRel("Contatos"));
  }
}