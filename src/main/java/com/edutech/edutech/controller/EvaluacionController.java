package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.EvaluacionModelAssembler;
import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con las evaluaciones")
@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionRepository evaluacionRepository;
    private final EvaluacionModelAssembler assembler;

    public EvaluacionController(EvaluacionRepository evaluacionRepository, EvaluacionModelAssembler assembler) {
        this.evaluacionRepository = evaluacionRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todas las evaluaciones")
    @GetMapping
    public CollectionModel<EntityModel<Evaluacion>> listar() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones, linkTo(methodOn(EvaluacionController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Crea una evaluación")
    @PostMapping
    public EntityModel<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionRepository.save(evaluacion);
        return assembler.toModel(nueva);
    }

    @Operation(summary = "Obtiene evaluación por ID")
    @GetMapping("/{id}")
    public EntityModel<Evaluacion> buscar(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id).orElse(null);
        return assembler.toModel(evaluacion);
    }

    @Operation(summary = "Actualiza una evaluación")
    @PutMapping("/{id}")
    public EntityModel<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        evaluacion.setId(id);
        Evaluacion actualizada = evaluacionRepository.save(evaluacion);
        return assembler.toModel(actualizada);
    }

    @Operation(summary = "Elimina una evaluación")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        evaluacionRepository.deleteById(id);
    }
}
