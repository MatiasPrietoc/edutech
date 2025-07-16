package com.edutech.edutech.controller;

import com.edutech.edutech.assembler.ProfesorModelAssembler;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.repository.ProfesorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.hateoas.EntityModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProfesorControllerTest {

    @Test
    public void testListarProfesores() {
        // Simular repositorio
        ProfesorRepository repo = Mockito.mock(ProfesorRepository.class);

        Profesor prof1 = new Profesor();
        prof1.setId(1L);
        prof1.setNombre("Profesor A");

        Profesor prof2 = new Profesor();
        prof2.setId(2L);
        prof2.setNombre("Profesor B");

        when(repo.findAll()).thenReturn(Arrays.asList(prof1, prof2));

        // Simular assembler
        ProfesorModelAssembler assembler = Mockito.mock(ProfesorModelAssembler.class);
        when(assembler.toModel(prof1)).thenReturn(EntityModel.of(prof1));
        when(assembler.toModel(prof2)).thenReturn(EntityModel.of(prof2));

        // Controlador con mocks
        ProfesorController controller = new ProfesorController(repo, assembler);

        List<EntityModel<Profesor>> resultado = controller.listar().getContent().stream().toList();

        assertEquals(2, resultado.size());
    }
}
