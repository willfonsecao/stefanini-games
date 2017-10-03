package br.com.stefanini.games.stefaninigamesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stefanini.games.stefaninigamesapi.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
	

}
