package br.com.fiap.studitproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class SenhaAtualizacaoRequest {
    private String id;
    private String senhaAtual;
    private String senhaNova;
}
