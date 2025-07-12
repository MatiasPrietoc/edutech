package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.CursoModelAssembler;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.repository.CursoRepository;
import com.edutech.edutech.exception.ResourceNotFoundException;

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
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;
    private final CursoModelAssembler assembler;

    public CursoController(CursoRepository cursoRepository, CursoModelAssembler assembler) {
        this.cursoRepository = cursoRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todos los cursos", description = "Devuelve todos los cursos registrados")
    @GetMapping
    public CollectionModel<EntityModel<Curso>> listar() {
        List<EntityModel<Curso>> cursos = cursoRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos, linkTo(methodOn(CursoController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Crea un curso", description = "Registra un nuevo curso en la base de datos")
    @PostMapping
    public EntityModel<Curso> crear(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoRepository.save(curso);
        return assembler.toModel(nuevoCurso);
    }

    @Operation(summary = "Obtiene un curso por ID", description = "Busca un curso por su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso encontrado"),
        @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Curso> buscar(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id " + id));
        return assembler.toModel(curso);
    }

    @Operation(summary = "Actualiza un curso", description = "Modifica los datos de un curso existente")
    @PutMapping("/{id}")
    public EntityModel<Curso> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id " + id));
        curso.setId(id);
        Curso actualizado = cursoRepository.save(curso);
        return assembler.toModel(actualizado);
    }

    @Operation(summary = "Elimina un curso", description = "Elimina un curso de la base de datos")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id " + id));
        cursoRepository.delete(curso);
    }
}
