package br.com.fiap.studitproject.controllers;

import br.com.fiap.studitproject.entities.Exercicio;
import br.com.fiap.studitproject.repositories.ExercicioRepository;
import br.com.fiap.studitproject.services.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercicio")
public class ExercicioController {
    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Exercicio exercicio){
        LocalDateTime agora = LocalDateTime.now();
        exercicio.setUltimaVisualizacao(agora);
        exercicioRepository.save(exercicio);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<Exercicio> getAll(@RequestParam(required = true) Long idUsuario,
                                  @PageableDefault(sort = "ultimaVisualizacao", direction = Sort.Direction.DESC,
                                          page = 0, size = 3) Pageable pageable) {
        return exercicioService.getAllExercicios(idUsuario, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> getById(@PathVariable Long id) {
        Optional<Exercicio> exercicio = exercicioService.getById(id);
        if(exercicio.isPresent()) {
            LocalDateTime agora = LocalDateTime.now();
            exercicio.get().setUltimaVisualizacao(agora);
            exercicioRepository.save(exercicio.get());
            return ResponseEntity.ok(exercicio.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Exercicio exercicio) {
        Optional<Exercicio> existingExercicio = exercicioService.getById(id);

        if (existingExercicio.isPresent()) {
            Exercicio updatedExercicio = existingExercicio.get();
            updatedExercicio.setPergunta(exercicio.getPergunta());
            updatedExercicio.setAlternativas(exercicio.getAlternativas());
            updatedExercicio.setResposta(exercicio.getResposta());
            updatedExercicio.setResolucao(exercicio.getResolucao());

            exercicioRepository.save(updatedExercicio);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Exercicio> exercicio = exercicioService.getById(id);

        if (exercicio.isPresent()) {
            exercicioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
