package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.ProfesorModelAssembler;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Profesores", description = "Operaciones relacionadas con los profesores")
@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;
    private final ProfesorModelAssembler assembler;

    public ProfesorController(ProfesorService profesorService, ProfesorModelAssembler assembler) {
        this.profesorService = profesorService;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todos los profesores")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Profesor>>> listar() {
        List<EntityModel<Profesor>> profesores = profesorService.listarProfesores()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(profesores, linkTo(methodOn(ProfesorController.class).listar()).withSelfRel())
        );
    }

    @Operation(summary = "Crea un profesor")
    @PostMapping
    public ResponseEntity<EntityModel<Profesor>> crear(@RequestBody Profesor profesor) {
        Profesor nuevo = profesorService.guardarProfesor(profesor);
        return ResponseEntity.ok(assembler.toModel(nuevo));
    }

    @Operation(summary = "Obtiene profesor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Profesor>> buscar(@PathVariable Long id) {
        return profesorService.obtenerPorId(id)
                .map(profesor -> ResponseEntity.ok(assembler.toModel(profesor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualiza un profesor")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Profesor>> actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        Profesor actualizado = profesorService.guardarProfesor(profesor);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Elimina un profesor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        profesorService.eliminarProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
