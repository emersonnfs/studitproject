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
@Table(name = "TB_STU_EXERCICIO")
public class Exercicio {
    @Id
    @GeneratedValue
    @Column(name = "ID_EXERCICIO")
    private Long id;

    @NotNull(message = "O nome do exercício é obrigatório")
    @Column(name = "PERGUNTA_EXERCICIO")
    private String pergunta;

    @ElementCollection
    @CollectionTable(name = "TB_STU_ALTERNATIVAS", joinColumns = @JoinColumn(name = "ID_EXERCICIO"))
    @Column(name = "ALTERNATIVA_EXERCICIO")
    private String[] alternativas;

    @NotNull(message = "A resposta do exercício é obrigatória")
    @Column(name = "RESPOSTA_EXERCICIO")
    private String resposta;

    @NotNull(message = "A Resolução do exercício é obrigatória")
    @Column(name = "RESOLUCAO_EXERCICIO")
    private String resolucao;

    @NotNull(message = "A matéria do exercício é obrigatória")
    @Column(name = "MATERIA_EXERCICIO")
    private MateriaEnum materia;

    @Column(name = "DATA_EXERCICIO")
    private LocalDateTime ultimaVisualizacao;

    @NotNull(message = "O usuário do exercício é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO")
    @OrderBy("ultimaVisualizacao DESC")
    private Usuario usuario;
}
