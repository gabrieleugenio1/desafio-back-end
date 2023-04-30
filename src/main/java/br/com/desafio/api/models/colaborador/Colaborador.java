package br.com.desafio.api.models.colaborador;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Colaborador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Colaborador {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[\\p{L}]+([,\\s][\\p{L}]+)*$", message = "Nome inválido")
	@NotBlank(message = "Nome não pode ser vazio")
	@NotNull 
	@Size(min=2,  message="Nome inválido")
    private String nome;
	 
	@Column(unique=true)
    @NotBlank(message = "CPF não pode ser vázio")	
    @NotNull 
    @Size(min=11, max=11, message = "CPF Inválido")
    private String cpf;
	
	@Column(name = "created_at",updatable = false)
    @CreationTimestamp
    @NotNull 
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDateTime createdAt = LocalDateTime.now();;
	
}
