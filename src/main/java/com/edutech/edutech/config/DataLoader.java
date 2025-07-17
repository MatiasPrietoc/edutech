package com.edutech.edutech.config;

import com.edutech.edutech.model.Curso;
import com.edutech.edutech.model.Evaluacion;
import com.edutech.edutech.model.Profesor;
import com.edutech.edutech.model.Usuario;
import com.edutech.edutech.repository.CursoRepository;
import com.edutech.edutech.repository.EvaluacionRepository;
import com.edutech.edutech.repository.ProfesorRepository;
import com.edutech.edutech.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProfesorRepository profesorRepository;
    private final CursoRepository cursoRepository;
    private final EvaluacionRepository evaluacionRepository;

    public DataLoader(UsuarioRepository usuarioRepository,
                      ProfesorRepository profesorRepository,
                      CursoRepository cursoRepository,
                      EvaluacionRepository evaluacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.profesorRepository = profesorRepository;
        this.cursoRepository = cursoRepository;
        this.evaluacionRepository = evaluacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Crear Usuarios
        Usuario u1 = new Usuario(null, "Ana", "ana@mail.com", "1234");
        Usuario u2 = new Usuario(null, "Luis", "luis@mail.com", "5678");
        usuarioRepository.save(u1);
        usuarioRepository.save(u2);

        // Crear Profesores
        Profesor p1 = new Profesor(null, "profe1@mail.com", "Matemáticas", "Profesor Juan");
        Profesor p2 = new Profesor(null, "profe2@mail.com", "Lenguaje", "Profesora Marta");
        profesorRepository.save(p1);
        profesorRepository.save(p2);

        // Crear Cursos
        Curso c1 = new Curso("Curso de Álgebra", 40, "Álgebra básica", p1);
        Curso c2 = new Curso("Curso de Literatura", 30, "Análisis de textos", p2);
        cursoRepository.save(c1);
        cursoRepository.save(c2);

        // Crear Evaluaciones
        Evaluacion e1 = new Evaluacion("Prueba Parcial", LocalDate.now(), 100, c1);
        Evaluacion e2 = new Evaluacion("Ensayo", LocalDate.now().plusDays(7), 100, c2);

        evaluacionRepository.save(e1);
        evaluacionRepository.save(e2);

        System.out.println("✅ Datos cargados en perfil DEV.");
    }
}
