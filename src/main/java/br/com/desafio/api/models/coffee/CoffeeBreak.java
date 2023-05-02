package br.com.desafio.api.models.coffee;


import java.sql.Date;   
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coffee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class CoffeeBreak {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_coffee;
	
	@Column(name = "dataCoffee",updatable = false)
    @NotNull 
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataCoffee;	
}
