package br.com.fiap.studitproject.controllers;

import br.com.fiap.studitproject.entities.Resumo;
import br.com.fiap.studitproject.repositories.ResumoRepository;
import br.com.fiap.studitproject.services.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/resumo")
public class ResumoController {
    @Autowired
    private ResumoService resumoService;

    @Autowired
    private ResumoRepository resumoRepository;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Resumo resumo){
        LocalDateTime agora = LocalDateTime.now();
        resumo.setUltimaVisualizacao(agora);
        resumoRepository.save(resumo);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<Resumo> getAll(@RequestParam(required = true) Long idUsuario,
                               @PageableDefault(sort = "ultimaVisualizacao", direction = Sort.Direction.DESC,
                                       page = 0, size = 3) Pageable pageable) {
        return resumoService.getAllResumos(idUsuario, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resumo> getById(@PathVariable Long id) {
        Optional<Resumo> resumo = resumoService.getById(id);
        if(resumo.isPresent()) {
            LocalDateTime agora = LocalDateTime.now();
            resumo.get().setUltimaVisualizacao(agora);
            resumoRepository.save(resumo.get());
            return ResponseEntity.ok(resumo.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Resumo resumo) {
        Optional<Resumo> existingResumo = resumoService.getById(id);

        if (existingResumo.isPresent()) {
            Resumo updatedResumo = existingResumo.get();
            updatedResumo.setNome(resumo.getNome());
            updatedResumo.setConteudo(resumo.getConteudo());

            resumoRepository.save(updatedResumo);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Resumo> resumo = resumoService.getById(id);

        if (resumo.isPresent()) {
            resumoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
