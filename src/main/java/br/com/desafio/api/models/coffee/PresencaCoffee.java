package br.com.desafio.api.models.coffee;


import br.com.desafio.api.models.colaborador.Colaborador;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "presenca")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 

public class PresencaCoffee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_presenca;
	
    @ManyToOne
    private CoffeeBreak coffeeBreak; 
		
    @ManyToOne
    private Colaborador colaborador; 
    
    private boolean presenca;

}
