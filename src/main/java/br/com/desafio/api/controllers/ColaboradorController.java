package br.com.desafio.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.api.models.colaborador.Colaborador;
import br.com.desafio.api.models.colaborador.ColaboradorDAO;
import br.com.desafio.api.utils.ValidarCPF;

@RestController
@RequestMapping("/colaborador/")
public class ColaboradorController {
	
    @Autowired       
	private ColaboradorDAO ColaboradorDAO; 
    
    @CrossOrigin	
    @GetMapping("/all")
	public List<Colaborador> index() {
    	List<Colaborador> colaboradores = ColaboradorDAO.encontrarTodosColaborador();
    	System.out.println(colaboradores);
		return colaboradores;
	};
	
    @CrossOrigin	
    @GetMapping("/encontrarCpf/{cpf}")
	public Colaborador encontrarCpf(@PathVariable String cpf) {
    	Colaborador colaboradores = ColaboradorDAO.encontrarPorCpf(cpf);
		return colaboradores;
	};
	
	@CrossOrigin
    @PostMapping("/cadastrar")
	public ResponseEntity<?>   CadastrarColaborador(@Validated @RequestBody Colaborador colaborador, BindingResult result) {
		if(colaborador.getCpf() != null && colaborador.getNome() != null) {
			if(ValidarCPF.validateCPF(colaborador.getCpf())) {
	    		int existeColaborador = ColaboradorDAO.countCpfNome(colaborador.getCpf(), colaborador.getNome());
	    		if(existeColaborador == 0) {
	    			ColaboradorDAO.inserirNovoColaborado(colaborador.getNome(), colaborador.getCpf(), colaborador.getCreatedAt());		
	    			 return ResponseEntity.ok().body("Criado com sucesso!");
	    		} else {	      
	    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF ou Nome cadastrado");
	            }  	   
			}
    	};
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome ou CPF inválido");
	};
	
	@CrossOrigin
    @PutMapping("/alterar")
	public ResponseEntity<?>   AlterarColaborador(@Validated @RequestBody Colaborador colaborador, BindingResult result) {
		if(colaborador.getCpf() != null && colaborador.getNome() != null) {
			if(ValidarCPF.validateCPF(colaborador.getCpf())) {
	    		int existeColaborador = ColaboradorDAO.countCpfNome(colaborador.getCpf(), colaborador.getNome());
	    		if(existeColaborador == 1 || existeColaborador == 0) {
	    			ColaboradorDAO.AlterarColaborador(colaborador.getNome(), colaborador.getCpf());		
	    			 return ResponseEntity.ok().body("Alterado com sucesso!");
	    		} else {	      
	    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome cadastrado");
	            }  	   
			}
    	};
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome ou CPF inválido");
	};
	
	@CrossOrigin	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?>  deletarColaborador(@PathVariable Double id) {
	    Colaborador colaborador = ColaboradorDAO.encontrarPorId(id);
	    if(colaborador == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao deletar");
	    }
	    ColaboradorDAO.deletarPorId(id);
	    return ResponseEntity.ok().body("Deletado com sucesso!");
	};
	
};
