package com.edutech.edutech.controller;

import com.edutech.edutech.model.Curso;
import com.edutech.edutech.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CursoControllerTest {

    @Test
    public void testListarCursos() {
        // Simular el repositorio
        CursoRepository repo = Mockito.mock(CursoRepository.class);
        Curso curso1 = new Curso();
        curso1.setNombre("Curso 1");
        Curso curso2 = new Curso();
        curso2.setNombre("Curso 2");

        when(repo.findAll()).thenReturn(Arrays.asList(curso1, curso2));

        // Crear controlador con repo simulado
        CursoController controller = new CursoController(repo);

        List<Curso> cursos = controller.listar();

        assertEquals(2, cursos.size());
        assertEquals("Curso 1", cursos.get(0).getNombre());
    }
}
