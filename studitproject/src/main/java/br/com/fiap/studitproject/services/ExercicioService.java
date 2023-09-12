package br.com.fiap.studitproject.services;

import br.com.fiap.studitproject.entities.Exercicio;
import br.com.fiap.studitproject.repositories.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;

    @Autowired
    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public Page<Exercicio> getAllExercicios(Long id, Pageable pageable) {
        return exercicioRepository.findAllByUsuarioId(id,pageable);
    }

    public Optional<Exercicio> getById(Long id) {
        return exercicioRepository.findById(id);
    }


    public Exercicio createExercicio(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }
}
