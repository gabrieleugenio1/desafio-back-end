package br.com.desafio.api.models.coffee.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeBreakDTO {
    private Long id_coffee;
    private LocalDate dataCoffee;
}