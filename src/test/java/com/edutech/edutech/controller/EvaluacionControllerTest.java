package com.edutech.edutech.controller;

import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EvaluacionControllerTest {
    private final EvaluacionRepository evaluacionRepository = mock(EvaluacionRepository.class);
    private final EvaluacionController controller = new EvaluacionController(evaluacionRepository);

    @Test
    void testListarEvaluaciones() {
        when(evaluacionRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(controller.listar().isEmpty());
    }

    @Test
    void testBuscarEvaluacion() {
        Evaluacion ev = new Evaluacion();
        ev.setId(1L);
        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(ev));
        assertEquals(1L, controller.buscar(1L).getId());
    }
}
