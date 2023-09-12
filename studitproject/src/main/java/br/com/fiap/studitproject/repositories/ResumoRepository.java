package br.com.fiap.studitproject.repositories;

import br.com.fiap.studitproject.entities.Exercicio;
import br.com.fiap.studitproject.entities.Resumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumoRepository extends JpaRepository<Resumo, Long> {
    @Query("SELECT r FROM Resumo r WHERE r.usuario.id = :idUsuario")
    Page<Resumo> findAllByUsuarioId(Long idUsuario, Pageable pageable);

    Optional<Resumo> findById(Long id);
}
