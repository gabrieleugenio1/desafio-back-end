package br.com.desafio.api.models.coffee.dao;

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
            "JOIN presenca p ON p.coffee_break_id_coffee = cof.id_coffee AND p.colaborador_id = co.id  ORDER BY cof.data_coffee DESC", nativeQuery = true)
	public List<Object[]> encontrarTodosCoffee();
	
	@Query(
			value = "SELECT * FROM coffee c WHERE c.data_coffee = :data", 
			nativeQuery = true)
	public CoffeeBreak findByDate(@Param("data") java.util.Date dataCoffe);
	
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
	public Long getLastId();

	/*INSERTS*/

	@Modifying
	@Transactional
	@Query(
		value = "INSERT INTO coffee (data_coffee) VALUES (:data)", 
		nativeQuery = true)
	public int createCoffee(@Param("data") java.util.Date dataCoffe);


}
