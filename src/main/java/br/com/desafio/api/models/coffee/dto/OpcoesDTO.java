package br.com.desafio.api.models.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpcoesDTO {
    private Long id_opcoes;
    private ColaboradorDTO colaborador;
    private String opcao;
}