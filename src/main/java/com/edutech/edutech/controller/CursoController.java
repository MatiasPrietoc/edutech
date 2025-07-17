package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.CursoModelAssembler;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Cursos", description = "Operaciones relacionadas con los cursos")
@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final CursoModelAssembler assembler;

    public CursoController(CursoService cursoService, CursoModelAssembler assembler) {
        this.cursoService = cursoService;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todos los cursos")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Curso>>> listar() {
        List<EntityModel<Curso>> cursos = cursoService.listarCursos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(cursos, linkTo(methodOn(CursoController.class).listar()).withSelfRel())
        );
    }

    @Operation(summary = "Crea un nuevo curso")
    @PostMapping
    public ResponseEntity<EntityModel<Curso>> crear(@RequestBody Curso curso) {
        Curso nuevo = cursoService.guardarCurso(curso);
        return ResponseEntity.ok(assembler.toModel(nuevo));
    }

    @Operation(summary = "Obtiene curso por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Curso>> buscar(@PathVariable Long id) {
        return cursoService.obtenerPorId(id)
                .map(curso -> ResponseEntity.ok(assembler.toModel(curso)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualiza un curso")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Curso>> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        Curso actualizado = cursoService.guardarCurso(curso);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Elimina un curso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
