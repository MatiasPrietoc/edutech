package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.UsuarioModelAssembler;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.UsuarioRepository;
import com.edutech.edutech.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.hateoas.EntityModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {

    @Test
    void testListarUsuarios() {

        // 1) Repositorio simulado
        UsuarioRepository repo = Mockito.mock(UsuarioRepository.class);

        Usuario u1 = new Usuario();
        u1.setId(1L);
        u1.setNombre("Ana");

        Usuario u2 = new Usuario();
        u2.setId(2L);
        u2.setNombre("Bruno");

        when(repo.findAll()).thenReturn(Arrays.asList(u1, u2));

        // 2) Assembler simulado
        UsuarioModelAssembler assembler = Mockito.mock(UsuarioModelAssembler.class);
        when(assembler.toModel(u1)).thenReturn(EntityModel.of(u1));
        when(assembler.toModel(u2)).thenReturn(EntityModel.of(u2));

        // 3) Instanciar el controlador con los mocks
        UsuarioService usuarioService = new UsuarioService(repo);
        UsuarioController controller = new UsuarioController(usuarioService, assembler);


        // 4) Ejecutar y verificar
        List<EntityModel<Usuario>> resultado =
                controller.listar().getContent().stream().toList();

        assertEquals(2, resultado.size());
    }
}
