package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.EvaluacionModelAssembler;
import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.service.EvaluacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EvaluacionControllerTest {

    @Mock
    private EvaluacionService evaluacionService;

    @Mock
    private EvaluacionModelAssembler assembler;

    @InjectMocks
    private EvaluacionController evaluacionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarEvaluacionPorId() {
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId(1L);
        when(evaluacionService.obtenerPorId(1L)).thenReturn(Optional.of(evaluacion));

        ResponseEntity<?> response = evaluacionController.buscar(1L);
        assertEquals(200, response.getStatusCode().value());
        verify(evaluacionService).obtenerPorId(1L);
    }
}
