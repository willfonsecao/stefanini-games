package br.com.stefanini.games.stefaninigamesapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.stefanini.games.stefaninigames.api.dto.response.CategoriaCampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.exception.rest.UnprocessableEntityException;
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
	
	@GetMapping(path = "/campeonatos/finalizados")
	public List<CategoriaCampeonatoDTOResponse> getCategoriasCampeonatosFinalizados(){
		return categoriaService.getCategoriasCampeonatosFinalizados();
	}
	
	@PostMapping
	public CategoriaCampeonato save(@RequestBody CategoriaCampeonato categoria){
		return this.categoriaService.save(categoria);
	}
	
	@PostMapping(path = "/logo")
	public void saveLogo(@RequestParam("logo") MultipartFile file) {
		String idCategoria = file.getOriginalFilename();
		categoriaService.saveLogo(idCategoria, getBytes(file));		
	}
	
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable Long id){
		this.categoriaService.delete(id);
	}
	
	private byte[] getBytes( MultipartFile file) {
		List<String> erros = new ArrayList<>();
		try {
			byte[] bytes = file != null ? file.getBytes() : null;
			return bytes;
		} catch (IOException e) {
			erros.add("Não foi possível salvar a logo. Tente novamente mais tarde.");
			throw new UnprocessableEntityException(erros);
		}

	}

}
