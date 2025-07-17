package com.edutech.edutech.service;

import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public List<Evaluacion> listarEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    public Optional<Evaluacion> obtenerPorId(Long id) {
        return evaluacionRepository.findById(id);
    }

    public Evaluacion guardarEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void eliminarEvaluacion(Long id) {
        evaluacionRepository.deleteById(id);
    }
}
