package com.edutech.edutech.controller;

import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.ProfesorRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorControllerTest {
    private final ProfesorRepository profesorRepository = mock(ProfesorRepository.class);
    private final ProfesorController controller = new ProfesorController(profesorRepository);

    @Test
    void testListarProfesores() {
        when(profesorRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(controller.listar().isEmpty());
    }

    @Test
    void testBuscarProfesor() {
        Profesor prof = new Profesor();
        prof.setId(1L);
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(prof));
        assertEquals(1L, controller.buscar(1L).getId());
    }
}
