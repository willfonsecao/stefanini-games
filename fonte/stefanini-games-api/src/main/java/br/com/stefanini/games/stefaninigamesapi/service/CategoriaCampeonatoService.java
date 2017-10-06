package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigames.api.dto.response.CampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigames.api.dto.response.CategoriaCampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;
import br.com.stefanini.games.stefaninigamesapi.model.Time;
import br.com.stefanini.games.stefaninigamesapi.repository.CategoriaCampeonatoRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.TimeRepository;

@Service
public class CategoriaCampeonatoService {
	
	@Autowired
	private CategoriaCampeonatoRepository categoriaRepository;

	@Autowired
	private TimeRepository timeRepository;
	
	public List<CategoriaCampeonatoDTOResponse> findAll(){
		return this.categoriaRepository.findAll()
				.stream().map((categoria) -> new CategoriaCampeonatoDTOResponse(categoria))
				.collect(Collectors.toList());
	}
	
	public List<CategoriaCampeonatoDTOResponse> getCategoriasAbertasParaInscricao(){
		List<CategoriaCampeonatoDTOResponse> categorias = this.categoriaRepository.getCategoriasAbertasParaInscricao(new Date())
				.stream().map((categoria) -> new CategoriaCampeonatoDTOResponse(categoria))
				.collect(Collectors.toList());
		categorias.forEach((cat)-> verificarVagasPreenchidasCampeonatos(cat));
		return categorias;
	}
	
	private void verificarVagasPreenchidasCampeonatos(CategoriaCampeonatoDTOResponse categoria){
		categoria.getCampeonatos().stream().forEach((c)-> verificarMaximoPreenchido(c));
	}
	
	private void verificarMaximoPreenchido(CampeonatoDTOResponse camp){
		List<Time> inscritos = timeRepository.getInscritos(camp.getId());
		camp.setVagasPreenchidas(isTotalPreenchido(inscritos.size(), camp.getMaxInscritos()));
	}
	
	private boolean isTotalPreenchido(int totalInscritos, Long maxInscritos){
		return totalInscritos == maxInscritos;
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
