package br.com.desafio.api.models.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PresencaDTO {
    private Long id_presenca;
    private ColaboradorDTO colaborador;
    private boolean presenca;
}