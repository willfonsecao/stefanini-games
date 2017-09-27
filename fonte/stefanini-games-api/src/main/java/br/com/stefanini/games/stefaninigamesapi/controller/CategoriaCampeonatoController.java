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

import br.com.stefanini.games.stefaninigames.api.dto.response.CategoriaCampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;
import br.com.stefanini.games.stefaninigamesapi.service.CategoriaCampeonatoService;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaCampeonatoController {
	
	@Autowired
	private CategoriaCampeonatoService categoriaService;
	
	@GetMapping
	public List<CategoriaCampeonatoDTOResponse> findAll(){
		return this.categoriaService.findAll();
	}
	
	@GetMapping(path = "/abertas")
	public List<CategoriaCampeonatoDTOResponse> getCategoriasAbertasParaInscricao(){
		return this.categoriaService.getCategoriasAbertasParaInscricao();
	}
	
	@PostMapping
	public CategoriaCampeonato save(@RequestBody CategoriaCampeonato categoria){
		return this.categoriaService.save(categoria);
	}
	
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable Long id){
		this.categoriaService.delete(id);
	}

}
