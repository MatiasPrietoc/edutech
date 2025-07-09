package com.edutech.edutech.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String especialidad;
}