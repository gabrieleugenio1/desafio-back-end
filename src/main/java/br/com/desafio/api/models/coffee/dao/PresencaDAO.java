package br.com.desafio.api.models.coffee.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.api.models.coffee.PresencaCoffee;

public interface PresencaDAO extends JpaRepository<PresencaCoffee, Long>{

	
	
	/*INSERTS*/
	@Modifying
	@Transactional
	@Query(
		value = "INSERT INTO presenca (presenca, coffee_break_id_coffee, colaborador_id) VALUES (:presenca, :id_coffee, :colaborador_id)", 
		nativeQuery = true)
	public int criarPresenca(@Param("presenca") boolean presenca, @Param("id_coffee") Long id_coffee, @Param("colaborador_id") Long colaborador_id);


	@Modifying
	@Transactional
	@Query(
		value = "UPDATE presenca SET presenca=1 WHERE coffee_break_id_coffee=:id_coffee AND colaborador_id=:id_colaborador", 
		nativeQuery = true)
	public int alterarPresenca(@Param("id_coffee") String id_coffee, @Param("id_colaborador") String id_colaborador);

}
