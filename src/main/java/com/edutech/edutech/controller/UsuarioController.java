package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.UsuarioModelAssembler;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioModelAssembler assembler;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioModelAssembler assembler) {
        this.usuarioRepository = usuarioRepository;
        this.assembler = assembler;
    }

    @Operation(summary = "Lista todos los usuarios", description = "Devuelve todos los usuarios registrados")
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> listar() {
        List<EntityModel<Usuario>> usuarios = usuarioRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios, linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Crea un usuario", description = "Registra un nuevo usuario en la base de datos")
    @PostMapping
    public EntityModel<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioRepository.save(usuario);
        return assembler.toModel(nuevo);
    }

    @Operation(summary = "Obtiene usuario por ID", description = "Busca un usuario por su identificador único")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Usuario> buscar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return assembler.toModel(usuario);
    }

    @Operation(summary = "Actualiza usuario", description = "Modifica los datos de un usuario existente")
    @PutMapping("/{id}")
    public EntityModel<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario actualizado = usuarioRepository.save(usuario);
        return assembler.toModel(actualizado);
    }

    @Operation(summary = "Elimina usuario", description = "Elimina un usuario de la base de datos")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
