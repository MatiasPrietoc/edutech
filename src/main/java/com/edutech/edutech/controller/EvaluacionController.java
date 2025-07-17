package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.EvaluacionModelAssembler;
import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con evaluaciones")
@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;
    private final EvaluacionModelAssembler assembler;

    public EvaluacionController(EvaluacionService evaluacionService, EvaluacionModelAssembler assembler) {
        this.evaluacionService = evaluacionService;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todas las evaluaciones", description = "Devuelve todas las evaluaciones registradas")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Evaluacion>>> listar() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.listarEvaluaciones()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(evaluaciones, linkTo(methodOn(EvaluacionController.class).listar()).withSelfRel())
        );
    }

    @Operation(summary = "Crea una evaluación", description = "Registra una nueva evaluación en la base de datos")
    @PostMapping
    public ResponseEntity<EntityModel<Evaluacion>> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nueva = evaluacionService.guardarEvaluacion(evaluacion);
        return ResponseEntity.ok(assembler.toModel(nueva));
    }

    @Operation(summary = "Obtiene evaluación por ID", description = "Busca una evaluación por su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Evaluacion>> buscar(@PathVariable Long id) {
        return evaluacionService.obtenerPorId(id)
                .map(evaluacion -> ResponseEntity.ok(assembler.toModel(evaluacion)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualiza evaluación", description = "Modifica los datos de una evaluación existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Evaluacion>> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        evaluacion.setId(id);
        Evaluacion actualizada = evaluacionService.guardarEvaluacion(evaluacion);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @Operation(summary = "Elimina evaluación", description = "Elimina una evaluación de la base de datos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        evaluacionService.eliminarEvaluacion(id);
        return ResponseEntity.noContent().build();
    }
}
