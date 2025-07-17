package com.edutech.edutech.service;

import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfesorServiceTest {

    private ProfesorRepository profesorRepository;
    private ProfesorService profesorService;

    @BeforeEach
    void setUp() {
        profesorRepository = mock(ProfesorRepository.class);
        profesorService = new ProfesorService(profesorRepository);
    }

    @Test
    void testListarProfesores() {
        when(profesorRepository.findAll()).thenReturn(List.of(new Profesor(), new Profesor()));
        assertEquals(2, profesorService.listarProfesores().size());
    }

    @Test
    void testObtenerPorId() {
        Profesor profe = new Profesor();
        profe.setId(1L);
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profe));

        assertTrue(profesorService.obtenerPorId(1L).isPresent());
    }

    @Test
    void testGuardarProfesor() {
        Profesor profe = new Profesor();
        when(profesorRepository.save(profe)).thenReturn(profe);

        assertEquals(profe, profesorService.guardarProfesor(profe));
    }

    @Test
    void testEliminarProfesor() {
        profesorService.eliminarProfesor(1L);
        verify(profesorRepository).deleteById(1L);
    }
}
