package br.com.desafio.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.api.models.coffee.CoffeeBreak;
import br.com.desafio.api.models.coffee.OpcoesCoffee;
import br.com.desafio.api.models.coffee.dao.CoffeeDAO;
import br.com.desafio.api.models.coffee.dao.OpcoesCoffeeDAO;
import br.com.desafio.api.models.colaborador.ColaboradorDAO;

@RestController
@RequestMapping("/coffeebreak/")
public class CoffeeController {
	
    @Autowired       
	private ColaboradorDAO ColaboradorDAO; 
    
    @Autowired       
	private CoffeeDAO CoffeeDAO; 
    
    @Autowired       
	private OpcoesCoffeeDAO OpcoesCoffeeDAO; 
    
    
    @CrossOrigin	
    @GetMapping("/all")
	public List<Object[]> index() {
    	List<Object[]> resultado = CoffeeDAO.encontrarTodosCoffee();
    	return resultado;
	};
    
	@CrossOrigin
    @PostMapping("/criar")
	public ResponseEntity<?>  CadastrarEntrarCoffe(@Validated @RequestBody CoffeeBreak coffeeBreak, BindingResult result) {
		System.out.print(coffeeBreak);
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar");
	};
};
