package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.CursoModelAssembler;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;
    private final CursoModelAssembler assembler;

    public CursoController(CursoRepository cursoRepository, CursoModelAssembler assembler) {
        this.cursoRepository = cursoRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todos los cursos")
    @GetMapping
    public CollectionModel<EntityModel<Curso>> listar() {
        List<EntityModel<Curso>> cursos = cursoRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos, linkTo(methodOn(CursoController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Crea un curso")
    @PostMapping
    public EntityModel<Curso> crear(@RequestBody Curso curso) {
        Curso nuevo = cursoRepository.save(curso);
        return assembler.toModel(nuevo);
    }

    @Operation(summary = "Obtiene curso por ID")
    @GetMapping("/{id}")
    public EntityModel<Curso> buscar(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        return assembler.toModel(curso);
    }

    @Operation(summary = "Actualiza un curso")
    @PutMapping("/{id}")
    public EntityModel<Curso> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso actualizado = cursoRepository.save(curso);
        return assembler.toModel(actualizado);
    }

    @Operation(summary = "Elimina un curso")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }
}
