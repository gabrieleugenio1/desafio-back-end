package br.com.desafio.api.models.coffee.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.api.models.coffee.OpcoesCoffee;

public interface OpcoesCoffeeDAO extends JpaRepository<OpcoesCoffee, Long>{

	@Query(
		value = "SELECT * FROM opcoes_coffee op WHERE op.opcao IN (:opcao) AND coffee_break_id_Coffee=:id_coffee",
		nativeQuery = true
	)
	public List<OpcoesCoffee> encontrarOpcoesIguais(@Param("opcao") List<String> todasOpcoes, @Param("id_coffee") Long id_coffee);

	@Query(
		value = "SELECT * FROM opcoes_coffee op WHERE op.colaborador_id = :colaborador AND op.coffee_break_id_Coffee = :id_coffee",
		nativeQuery = true
	)
	public List<OpcoesCoffee> verificarColaboradorInserido(@Param("colaborador") Long long1, @Param("id_coffee") Long id_coffee);


    @Modifying
	@Transactional
	@Query(
			value = "INSERT INTO opcoes_coffee (opcao, coffee_break_id_coffee, colaborador_id) VALUES (:opcao, :id_coffee, :id_colaborador) ",
			nativeQuery = true)
	public int createCoffeeOptions(@Param("opcao") String opcao, @Param("id_coffee") Long id_coffee, @Param("id_colaborador") Long id_colaborador);

}
