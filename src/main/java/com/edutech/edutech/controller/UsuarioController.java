package com.edutech.edutech.controller;

import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(summary = "Lista todos los usuarios", description = "Devuelve todos los usuarios registrados")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Operation(summary = "Crea un usuario", description = "Registra un nuevo usuario en la base de datos")
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Obtiene usuario por ID", description = "Busca un usuario por su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Actualiza usuario", description = "Modifica los datos de un usuario existente")
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    @Operation(summary = "Elimina usuario", description = "Elimina un usuario de la base de datos")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}