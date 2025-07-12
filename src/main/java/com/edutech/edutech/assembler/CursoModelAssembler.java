package com.edutech.edutech.assembler;

import com.edutech.edutech.controller.CursoController;
import com.edutech.edutech.model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoController.class).buscar(curso.getId())).withSelfRel(),
                linkTo(methodOn(CursoController.class).listar()).withRel("cursos"));
                linkTo(methodOn(CursoController.class).actualizar(curso.getId(), curso)).withRel("actualizar"),
                linkTo(methodOn(CursoController.class).eliminar(curso.getId())).withRel("eliminar")
        );
    }
}
