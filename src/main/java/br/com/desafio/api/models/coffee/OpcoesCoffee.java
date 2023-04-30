package br.com.desafio.api.models.coffee;


import br.com.desafio.api.models.colaborador.Colaborador;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "opcoes_coffee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class OpcoesCoffee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_opcoes;
	
    @ManyToOne
    private CoffeeBreak coffeeBreak; 
		
    @ManyToOne
    private Colaborador colaborador; 
    
    @NotBlank(message = "Opção é obrigatória")
    private String opcao;

}
