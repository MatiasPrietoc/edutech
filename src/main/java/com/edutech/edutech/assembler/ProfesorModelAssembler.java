package com.edutech.edutech.assembler;

import com.edutech.edutech.controller.ProfesorController;
import com.edutech.edutech.model.Profesor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>> {

    @Override
    public EntityModel<Profesor> toModel(Profesor profesor) {
        return EntityModel.of(profesor,
                linkTo(methodOn(ProfesorController.class).buscar(profesor.getId())).withSelfRel(),
                linkTo(methodOn(ProfesorController.class).listar()).withRel("profesores"),
                linkTo(methodOn(ProfesorController.class).actualizar(profesor.getId(), profesor)).withRel("actualizar"),
                linkTo(methodOn(ProfesorController.class).eliminar(profesor.getId())).withRel("eliminar")
        );
    }
}
