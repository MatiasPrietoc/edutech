package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.ProfesorModelAssembler;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.ProfesorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Profesores", description = "Operaciones relacionadas con los profesores")
@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorRepository profesorRepository;
    private final ProfesorModelAssembler assembler;

    public ProfesorController(ProfesorRepository profesorRepository, ProfesorModelAssembler assembler) {
        this.profesorRepository = profesorRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todos los profesores")
    @GetMapping
    public CollectionModel<EntityModel<Profesor>> listar() {
        List<EntityModel<Profesor>> profesores = profesorRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(profesores, linkTo(methodOn(ProfesorController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Crea un profesor")
    @PostMapping
    public EntityModel<Profesor> crear(@RequestBody Profesor profesor) {
        Profesor nuevo = profesorRepository.save(profesor);
        return assembler.toModel(nuevo);
    }

    @Operation(summary = "Obtiene profesor por ID")
    @GetMapping("/{id}")
    public EntityModel<Profesor> buscar(@PathVariable Long id) {
        Profesor profesor = profesorRepository.findById(id).orElse(null);
        return assembler.toModel(profesor);
    }

    @Operation(summary = "Actualiza un profesor")
    @PutMapping("/{id}")
    public EntityModel<Profesor> actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        Profesor actualizado = profesorRepository.save(profesor);
        return assembler.toModel(actualizado);
    }

    @Operation(summary = "Elimina un profesor")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        profesorRepository.deleteById(id);
    }
}
