package br.com.desafio.api.models.colaborador;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ColaboradorDAO extends JpaRepository<Colaborador, Long>{

	/*SELECTS */
	@Query(
			  value = "SELECT * FROM colaborador ", 
			  nativeQuery = true)
	public List<Colaborador>  encontrarTodosColaborador();
	
	@Query(
			  value = "SELECT * FROM colaborador c WHERE c.cpf = :cpf", 
			  nativeQuery = true)
	public Colaborador encontrarPorCpf(@Param("cpf") String cpf);
	
	@Query(
			  value = "SELECT COUNT(*) FROM colaborador c WHERE c.cpf = :cpf or LOWER(c.nome) = LOWER(:nome)", 
			  nativeQuery = true)
	public int countCpfNome(@Param("cpf") String cpf, @Param("nome") String nome);
	
	@Query(
			  value = "SELECT * FROM colaborador c WHERE c.id=:id", 
			  nativeQuery = true)
	public Colaborador encontrarPorId(@Param("id") Double id);
	
	/*INSERTS*/
	
	@Modifying
	@Transactional
	@Query(
			  value = "UPDATE colaborador c SET nome = :nome WHERE c.cpf=:cpf", 
			  nativeQuery = true)
	public int AlterarColaborador(@Param("nome") String nome, @Param("cpf") String cpf);
	
	@Modifying
	@Transactional
	@Query(
			  value = "INSERT INTO colaborador (nome, cpf,created_at) VALUES (:nome, :cpf, :created_at)", 
			  nativeQuery = true)
	public int inserirNovoColaborado(@Param("nome") String nome, @Param("cpf") String cpf, @Param("created_at") LocalDateTime created_at);
	
	
	/*DELETE*/

	@Modifying
	@Transactional
	@Query(
			  value = 	"DELETE FROM colaborador WHERE id=:id", 
			  nativeQuery = true)
	public void deletarPorId(@Param("id") Double id);
	
	
	

	
}
