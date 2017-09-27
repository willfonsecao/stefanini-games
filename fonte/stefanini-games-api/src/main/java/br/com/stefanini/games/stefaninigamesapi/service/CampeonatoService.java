package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigames.api.dto.response.CampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.exception.rest.UnprocessableEntityException;
import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.repository.CampeonatoRepository;

@Service
public class CampeonatoService {
	
	@Autowired
	private CampeonatoRepository campeonatoRepository;
	
	public List<CampeonatoDTOResponse> findAll(){
		return this.campeonatoRepository.findAll()
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
	}
	
	public Campeonato save(Campeonato campeonato){
		this.validarCampeonato(campeonato);
		return this.campeonatoRepository.save(campeonato);
	}
	
	public void delete(Long id){
		this.campeonatoRepository.delete(id);
	}
	
	private void validarCampeonato(Campeonato param){
		List<String> erros = new ArrayList<>();

		if (param.getCategoria().getId() == null) {
			erros.add("O campo Categoria é obrigatório.");
		}

		if (!erros.isEmpty()) {
			throw new UnprocessableEntityException(erros);
		}
	}

}
