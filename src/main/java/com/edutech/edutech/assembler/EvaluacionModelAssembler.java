package com.edutech.edutech.assembler;

import com.edutech.edutech.controller.EvaluacionController;
import com.edutech.edutech.model.Evaluacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(evaluacion,
                linkTo(methodOn(EvaluacionController.class).buscar(evaluacion.getId())).withSelfRel(),
                linkTo(methodOn(EvaluacionController.class).listar()).withRel("evaluaciones"));
    }
}
