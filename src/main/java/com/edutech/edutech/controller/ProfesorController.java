package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.ProfesorModelAssembler;
import com.edutech.edutech.exception.ResourceNotFoundException;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.ProfesorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorRepository profesorRepository;
    private final ProfesorModelAssembler assembler;

    public ProfesorController(ProfesorRepository profesorRepository, ProfesorModelAssembler assembler) {
        this.profesorRepository = profesorRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todos los profesores", description = "Devuelve todos los profesores registrados")
    @GetMapping
    public CollectionModel<EntityModel<Profesor>> listar() {
        List<EntityModel<Profesor>> profesores = profesorRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(profesores, linkTo(methodOn(ProfesorController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Crea un profesor", description = "Registra un nuevo profesor en la base de datos")
    @PostMapping
    public EntityModel<Profesor> crear(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorRepository.save(profesor);
        return assembler.toModel(nuevoProfesor);
    }

    @Operation(summary = "Obtiene profesor por ID", description = "Busca un profesor por su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Profesor> buscar(@PathVariable Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con ID: " + id));
        return assembler.toModel(profesor);
    }

    @Operation(summary = "Actualiza profesor", description = "Modifica los datos de un profesor existente")
    @PutMapping("/{id}")
    public EntityModel<Profesor> actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        if (!profesorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Profesor no encontrado con ID: " + id);
        }
        profesor.setId(id);
        Profesor actualizado = profesorRepository.save(profesor);
        return assembler.toModel(actualizado);
    }

    @Operation(summary = "Elimina profesor", description = "Elimina un profesor de la base de datos")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Profesor no encontrado con ID: " + id);
        }
        profesorRepository.deleteById(id);
    }
}
