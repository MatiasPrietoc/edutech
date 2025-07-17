package com.edutech.edutech.service;

import com.edutech.edutech.model.Curso;
import com.edutech.edutech.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceTest {

    private CursoRepository cursoRepository;
    private CursoService cursoService;

    @BeforeEach
    void setUp() {
        cursoRepository = mock(CursoRepository.class);
        cursoService = new CursoService(cursoRepository);
    }

    @Test
    void testListarCursos() {
        when(cursoRepository.findAll()).thenReturn(List.of(new Curso(), new Curso()));
        assertEquals(2, cursoService.listarCursos().size());
    }

    @Test
    void testObtenerPorId() {
        Curso curso = new Curso();
        curso.setId(1L);
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        assertTrue(cursoService.obtenerPorId(1L).isPresent());
    }

    @Test
    void testGuardarCurso() {
        Curso curso = new Curso();
        when(cursoRepository.save(curso)).thenReturn(curso);

        assertEquals(curso, cursoService.guardarCurso(curso));
    }

    @Test
    void testEliminarCurso() {
        cursoService.eliminarCurso(1L);
        verify(cursoRepository).deleteById(1L);
    }
}
