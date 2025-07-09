package com.edutech.edutech.controller;

import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionController(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    @Operation(summary = "Lista todas las evaluaciones", description = "Devuelve todas las evaluaciones registradas")
    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    @Operation(summary = "Crea una evaluación", description = "Registra una nueva evaluación en la base de datos")
    @PostMapping
    public Evaluacion crear(@RequestBody Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    @Operation(summary = "Obtiene evaluación por ID", description = "Busca una evaluación por su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
        @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    })
    @GetMapping("/{id}")
    public Evaluacion buscar(@PathVariable Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Actualiza evaluación", description = "Modifica los datos de una evaluación existente")
    @PutMapping("/{id}")
    public Evaluacion actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        evaluacion.setId(id);
        return evaluacionRepository.save(evaluacion);
    }

    @Operation(summary = "Elimina evaluación", description = "Elimina una evaluación de la base de datos")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        evaluacionRepository.deleteById(id);
    }
}