package com.edutech.edutech.model;

import jakarta.persistence.*;
import lombok.Data;
//import com.edutech.edutech.model.Profesor; lo mas probable es que no se use //

@Entity
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private int duracionHoras;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
}
