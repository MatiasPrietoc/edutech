package com.edutech.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int duracion; // duración en horas, por ejemplo
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    // Constructor personalizado sin ID (usado en DataLoader)
    public Curso(String nombre, int duracion, String descripcion, Profesor profesor) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.profesor = profesor;
    }
}
