package br.com.fiap.studitproject.services;

import br.com.fiap.studitproject.entities.Resumo;
import br.com.fiap.studitproject.repositories.ResumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumoService {
    private final ResumoRepository resumoRepository;

    @Autowired
    public ResumoService(ResumoRepository resumoRepository) {
        this.resumoRepository = resumoRepository;
    }

    public Page<Resumo> getAllResumos(Long id, Pageable pageable) {
        return resumoRepository.findAllByUsuarioId(id,pageable);
    }

    public Optional<Resumo> getById(Long id) {
        return resumoRepository.findById(id);
    }
}
