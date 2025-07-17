package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.ProfesorModelAssembler;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.service.ProfesorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfesorControllerTest {

    @Mock
    private ProfesorService profesorService;

    @Mock
    private ProfesorModelAssembler assembler;

    @InjectMocks
    private ProfesorController profesorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarProfesorPorId() {
        Profesor profesor = new Profesor();
        profesor.setId(1L);
        when(profesorService.obtenerPorId(1L)).thenReturn(Optional.of(profesor));

        ResponseEntity<?> response = profesorController.buscar(1L);
        assertEquals(200, response.getStatusCode().value());
        verify(profesorService).obtenerPorId(1L);
    }
}
