package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.CursoModelAssembler;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CursoControllerTest {

    @Mock
    private CursoService cursoService;

    @Mock
    private CursoModelAssembler assembler;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarCursoPorId() {
        Curso curso = new Curso();
        curso.setId(1L);
        when(cursoService.obtenerPorId(1L)).thenReturn(Optional.of(curso));

        ResponseEntity<?> response = cursoController.buscar(1L);
        assertEquals(200, response.getStatusCode().value());
        verify(cursoService).obtenerPorId(1L);
    }
}
