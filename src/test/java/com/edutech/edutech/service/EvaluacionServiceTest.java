package com.edutech.edutech.service;

import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EvaluacionServiceTest {

    private EvaluacionRepository evaluacionRepository;
    private EvaluacionService evaluacionService;

    @BeforeEach
    void setUp() {
        evaluacionRepository = mock(EvaluacionRepository.class);
        evaluacionService = new EvaluacionService(evaluacionRepository);
    }

    @Test
    void testListarEvaluaciones() {
        when(evaluacionRepository.findAll()).thenReturn(List.of(new Evaluacion(), new Evaluacion()));
        assertEquals(2, evaluacionService.listarEvaluaciones().size());
    }

    @Test
    void testObtenerPorId() {
        Evaluacion eval = new Evaluacion();
        eval.setId(1L);
        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(eval));

        assertTrue(evaluacionService.obtenerPorId(1L).isPresent());
    }

    @Test
    void testGuardarEvaluacion() {
        Evaluacion eval = new Evaluacion();
        when(evaluacionRepository.save(eval)).thenReturn(eval);

        assertEquals(eval, evaluacionService.guardarEvaluacion(eval));
    }

    @Test
    void testEliminarEvaluacion() {
        evaluacionService.eliminarEvaluacion(1L);
        verify(evaluacionRepository).deleteById(1L);
    }
}
