package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;
import br.com.stefanini.games.stefaninigamesapi.repository.CategoriaCampeonatoRepository;

@Service
public class CategoriaCampeonatoService {
	
	@Autowired
	private CategoriaCampeonatoRepository categoriaRepository;
	
	public List<CategoriaCampeonato> findAll(){
		return this.categoriaRepository.findAll();
	}
	
	public CategoriaCampeonato save(CategoriaCampeonato categoria){
		return this.categoriaRepository.save(categoria);
	}
	
	public void delete(Long id){
		this.categoriaRepository.delete(id);
	}

}
