package com.edutech.edutech.service;

import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void testListarUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.listarUsuarios();
        assertEquals(2, result.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void testObtenerPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.obtenerPorId(1L);
        assertTrue(result.isPresent());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.guardarUsuario(usuario);
        assertEquals(usuario, saved);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testEliminarUsuario() {
        Long id = 1L;
        usuarioService.eliminarUsuario(id);
        verify(usuarioRepository).deleteById(id);
    }
}
