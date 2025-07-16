package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.CursoModelAssembler;
import com.edutech.edutech.model.Curso;
import com.edutech.edutech.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.hateoas.EntityModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CursoControllerTest {

    @Test
    public void testListarCursos() {
        // Simulación del repositorio
        CursoRepository repo = Mockito.mock(CursoRepository.class);

        Curso curso1 = new Curso();
        curso1.setId(1L);
        curso1.setNombre("Curso 1");

        Curso curso2 = new Curso();
        curso2.setId(2L);
        curso2.setNombre("Curso 2");

        when(repo.findAll()).thenReturn(Arrays.asList(curso1, curso2));

        // Simulación del assembler
        CursoModelAssembler assembler = Mockito.mock(CursoModelAssembler.class);

        when(assembler.toModel(curso1)).thenReturn(EntityModel.of(curso1));
        when(assembler.toModel(curso2)).thenReturn(EntityModel.of(curso2));

        // Crear el controlador con mocks
        CursoController controller = new CursoController(repo, assembler);

        List<EntityModel<Curso>> resultado = controller.listar().getContent().stream().toList();

        assertEquals(2, resultado.size());
    }
}
