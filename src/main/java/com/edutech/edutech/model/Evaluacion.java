package com.edutech.edutech.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.edutech.edutech.model.Curso;

@Entity
@Data
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private LocalDate fecha;
    private double puntajeMaximo;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
