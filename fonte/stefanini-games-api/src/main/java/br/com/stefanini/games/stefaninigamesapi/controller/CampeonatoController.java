package br.com.stefanini.games.stefaninigamesapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stefanini.games.stefaninigames.api.dto.response.CampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.service.CampeonatoService;

@CrossOrigin
@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
	
	@Autowired
	private CampeonatoService campeonatoService;
	
	@GetMapping
	public List<CampeonatoDTOResponse> getCampeonatos(){
		return this.campeonatoService.findAll();
	}
	
	@PostMapping
	public Campeonato save(@RequestBody Campeonato campeonato){
		return this.campeonatoService.save(campeonato);
	}
	
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable Long id){
		this.campeonatoService.delete(id);
	}

}
