package br.com.fiap.studitproject.repositories;

import br.com.fiap.studitproject.entities.Exercicio;
import com.sun.source.tree.ParenthesizedTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    @Query("SELECT e FROM Exercicio e WHERE e.usuario.id = :idUsuario")
    Page<Exercicio> findAllByUsuarioId(Long idUsuario, Pageable pageable);
    Optional<Exercicio> findById(Long id);
}
