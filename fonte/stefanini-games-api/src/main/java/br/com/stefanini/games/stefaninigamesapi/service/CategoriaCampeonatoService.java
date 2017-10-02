package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigames.api.dto.response.CategoriaCampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;
import br.com.stefanini.games.stefaninigamesapi.repository.CategoriaCampeonatoRepository;

@Service
public class CategoriaCampeonatoService {
	
	@Autowired
	private CategoriaCampeonatoRepository categoriaRepository;
	
	public List<CategoriaCampeonatoDTOResponse> findAll(){
		return this.categoriaRepository.findAll()
				.stream().map((categoria) -> new CategoriaCampeonatoDTOResponse(categoria))
				.collect(Collectors.toList());
	}
	
	public List<CategoriaCampeonatoDTOResponse> getCategoriasAbertasParaInscricao(){
		return this.categoriaRepository.getCategoriasAbertasParaInscricao(new Date())
				.stream().map((categoria) -> new CategoriaCampeonatoDTOResponse(categoria))
				.collect(Collectors.toList());
	}
	
	public CategoriaCampeonato save(CategoriaCampeonato categoria){
		return this.categoriaRepository.save(categoria);
	}
	
	public void saveLogo(String idCategoria, byte[] bytes){
		CategoriaCampeonato categoria = categoriaRepository.findOne(Long.valueOf(idCategoria));
		categoria.setLogo(bytes);
		categoriaRepository.save(categoria);
	}

	public void delete(Long id){
		this.categoriaRepository.delete(id);
	}

}
