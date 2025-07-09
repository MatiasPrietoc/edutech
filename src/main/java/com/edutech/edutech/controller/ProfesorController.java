package com.edutech.edutech.controller;

import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.ProfesorRepository;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorRepository profesorRepository;

    public ProfesorController(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Operation(summary = "Lista todos los profesores", description = "Devuelve todos los profesores registrados")
    @GetMapping
    public List<Profesor> listar() {
        return profesorRepository.findAll();
    }

    @Operation(summary = "Crea un profesor", description = "Registra un nuevo profesor en la base de datos")
    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Operation(summary = "Obtiene profesor por ID", description = "Busca un profesor por su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
        @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    @GetMapping("/{id}")
    public Profesor buscar(@PathVariable Long id) {
        return profesorRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Actualiza profesor", description = "Modifica los datos de un profesor existente")
    @PutMapping("/{id}")
    public Profesor actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        return profesorRepository.save(profesor);
    }

    @Operation(summary = "Elimina profesor", description = "Elimina un profesor de la base de datos")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        profesorRepository.deleteById(id);
    }
}