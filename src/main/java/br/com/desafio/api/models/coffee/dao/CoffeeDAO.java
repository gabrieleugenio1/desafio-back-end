package br.com.desafio.api.models.coffee.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.api.models.coffee.CoffeeBreak;

public interface CoffeeDAO extends JpaRepository<CoffeeBreak, Long>{
	@Query(value = "SELECT * FROM coffee cof " +
            "JOIN opcoes_coffee op ON cof.id_coffee = op.coffee_break_id_coffee " +
            "JOIN colaborador co ON co.id = op.colaborador_id " +
            "JOIN presenca p ON p.coffee_break_id_coffee = cof.id_coffee AND p.colaborador_id = co.id", nativeQuery = true)
	public List<Object[]> encontrarTodosCoffee();
	
	@Query(
			  value = "SELECT COUNT(*) FROM colaborador c WHERE c.cpf = :cpf", 
			  nativeQuery = true)
	public int findByCPF(@Param("cpf") String cpf);
	
	
	@Modifying
	@Transactional
	@Query(
			  value = "INSERT INTO colaborador (nome, cpf,created_at) VALUES (:nome, :cpf, :created_at)", 
			  nativeQuery = true)
	public int inserirNovoColaborado(@Param("nome") String nome, @Param("cpf") String cpf, @Param("created_at") LocalDateTime created_at);
}
