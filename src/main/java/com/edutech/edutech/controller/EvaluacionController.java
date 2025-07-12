package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.EvaluacionModelAssembler;
import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionRepository evaluacionRepository;
    private final EvaluacionModelAssembler assembler;

    public EvaluacionController(EvaluacionRepository evaluacionRepository, EvaluacionModelAssembler assembler) {
        this.evaluacionRepository = evaluacionRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todas las evaluaciones", description = "Devuelve todas las evaluaciones registradas")
    @GetMapping
    public CollectionModel<EntityModel<Evaluacion>> listar() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones,
                linkTo(methodOn(EvaluacionController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Crea una evaluación", description = "Registra una nueva evaluación en la base de datos")
    @PostMapping
    public EntityModel<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionRepository.save(evaluacion);
        return assembler.toModel(nueva);
    }

    @Operation(summary = "Obtiene evaluación por ID", description = "Busca una evaluación por su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @GetMapping("/{id}")
    public EntityModel<Evaluacion> buscar(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluación no encontrada"));
        return assembler.toModel(evaluacion);
    }

    @Operation(summary = "Actualiza evaluación", description = "Modifica los datos de una evaluación existente")
    @PutMapping("/{id}")
    public EntityModel<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        evaluacion.setId(id);
        Evaluacion actualizada = evaluacionRepository.save(evaluacion);
        return assembler.toModel(actualizada);
    }

    @Operation(summary = "Elimina evaluación", description = "Elimina una evaluación de la base de datos")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        evaluacionRepository.deleteById(id);
    }
}
