package com.edutech.edutech.repository;

import com.edutech.edutech.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}