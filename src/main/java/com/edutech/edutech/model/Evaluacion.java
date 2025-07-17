package com.edutech.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Constructor personalizado sin ID (usado en DataLoader)
    public Evaluacion(String tipo, LocalDate fecha, double puntajeMaximo, Curso curso) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.puntajeMaximo = puntajeMaximo;
        this.curso = curso;
    }
}
