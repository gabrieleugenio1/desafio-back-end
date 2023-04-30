package br.com.desafio.api.models.coffee.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.api.models.coffee.PresencaCoffee;

public interface PresencaDAO extends JpaRepository<PresencaCoffee, Long>{

	
	
}
