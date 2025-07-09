package com.edutech.edutech.controller;

import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {
    private final UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
    private final UsuarioController controller = new UsuarioController(usuarioRepository);

    @Test
    void testListarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(controller.listar().isEmpty());
    }

    @Test
    void testBuscarUsuario() {
        Usuario user = new Usuario();
        user.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(1L, controller.buscar(1L).getId());
    }
}
