package br.com.desafio.api.controllers;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.api.models.coffee.CoffeeBreak;
import br.com.desafio.api.models.coffee.OpcoesCoffee;
import br.com.desafio.api.models.coffee.dao.CoffeeDAO;
import br.com.desafio.api.models.coffee.dao.OpcoesCoffeeDAO;
import br.com.desafio.api.models.coffee.dao.PresencaDAO;
import br.com.desafio.api.models.colaborador.Colaborador;
import br.com.desafio.api.models.colaborador.ColaboradorDAO;

@RestController
@RequestMapping("/coffeebreak")
public class CoffeeController {
	
    @Autowired       
	private ColaboradorDAO ColaboradorDAO; 
    
    @Autowired       
	private CoffeeDAO CoffeeDAO; 
    
    @Autowired       
	private OpcoesCoffeeDAO OpcoesCoffeeDAO; 
    
	@Autowired       
	private PresencaDAO presencaDAO; 
    
    
    @CrossOrigin	
    @GetMapping("all")
	public List<Object[]> index() {
    	List<Object[]> resultado = CoffeeDAO.encontrarTodosCoffee();
    	return resultado;
	};
    
	@CrossOrigin
    @PostMapping("criar")
	public ResponseEntity<?>  CadastrarEntrarCoffe(@RequestBody Map<String, Object> requests) {
		String dataCoffeString = (String) requests.get("data_coffee");
		String cpf = (String) requests.get("cpf");
		String nome = (String) requests.get("nome");
		List<Map<String, Object>> opcoes = (List<Map<String, Object>>) requests.get("items");
		
		if(dataCoffeString != null && cpf != null && nome != null && dataCoffeString.length() > 7){
			Colaborador colaborador = ColaboradorDAO.encontrarPoCpfNome(cpf, nome);
			if(colaborador != null){
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date dataCoffe = format.parse(dataCoffeString);
					CoffeeBreak coffee = CoffeeDAO.findByDate(dataCoffe);
					if(coffee == null){
						CoffeeDAO.createCoffee(dataCoffe);
						Long id_coffee = CoffeeDAO.getLastId();
						List<String> todasOpcoes = opcoes.stream()
						.map(opcao -> (String) opcao.get("items"))
						.collect(Collectors.toList());

						for (String opcao : todasOpcoes) {
							OpcoesCoffeeDAO.createCoffeeOptions(opcao, id_coffee, colaborador.getId()); 
						}

						System.out.println(id_coffee);
						presencaDAO.criarPresenca(false, id_coffee, colaborador.getId());
						return ResponseEntity.ok().body("Criado com sucesso!");
					}else{
						List<String> todasOpcoes = opcoes.stream()
						.map(opcao -> (String) opcao.get("items"))
						.collect(Collectors.toList());
					
					List<OpcoesCoffee> opcoesExistentes = OpcoesCoffeeDAO.encontrarOpcoesIguais(todasOpcoes, coffee.getId_coffee());
					
					List<OpcoesCoffee> verificarColaboradorInserido = OpcoesCoffeeDAO.verificarColaboradorInserido(colaborador.getId(), coffee.getId_coffee());
					
					if (verificarColaboradorInserido == null || verificarColaboradorInserido.isEmpty()) {
						for (String opcao : todasOpcoes) {
							boolean opcaoJaExiste = opcoesExistentes.stream().anyMatch(o -> o.getOpcao().equals(opcao));
							if (opcaoJaExiste) {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A opção '" + opcao + "' já existe!");
							} 
						}
					
						for (String opcao : todasOpcoes) {
							OpcoesCoffeeDAO.createCoffeeOptions(opcao, coffee.getId_coffee(), colaborador.getId()); 
						}		
						presencaDAO.criarPresenca(false, coffee.getId_coffee(), colaborador.getId());	
					
						return ResponseEntity.ok().body("Entrou com sucesso no Coffee");
					}
					
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O colaborador já foi cadastrado para este coffee.");
										
					}
				} catch (ParseException e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data inválida: " + dataCoffeString);
				}
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Colaborador não existe");
			}
		}

	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar");
	};

	@CrossOrigin
    @PutMapping("marcarpresenca/{id_colaborador}/{id_coffee}")
	public ResponseEntity<?>  AlterarPresenca(@PathVariable String id_colaborador, @PathVariable String id_coffee) {
		
		int res = presencaDAO.alterarPresenca(id_coffee, id_colaborador);
		if(res == 1){
			return ResponseEntity.ok().body("Presença marcada!");
		}
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao marcar presença");
	};


};
