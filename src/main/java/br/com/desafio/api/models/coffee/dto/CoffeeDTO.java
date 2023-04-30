package br.com.desafio.api.models.coffee.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeDTO {
    private Long id_coffee;
    private LocalDate dataCoffee;
    private List<OpcoesDTO> opcoes;
    private List<PresencaDTO> presencas;
}