package br.com.fiap.studitproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "TB_STU_RESUMO")
public class Resumo {

    @Id
    @GeneratedValue
    @Column(name = "ID_RESUMO")
    private Long id;

    @NotNull(message = "O nome do resumo é obrigatório")
    @Column(name = "NOME_RESUMO")
    private String nome;

    @NotNull(message = "O conteúdo do resumo é obrigatório")
    @Column(name = "CONTEUDO_RESUMO")
    private String conteudo;

    @NotNull(message = "A matéria do resumo é obrigatória")
    @Column(name = "MATERIA_RESUMO")
    private MateriaEnum materia;

    @Column(name = "DATA_RESUMO")
    private LocalDateTime ultimaVisualizacao;

    @NotNull(message = "O usuário do resumo é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO")
    @OrderBy("ultimaVisualizacao DESC")
    private Usuario usuario;
}
