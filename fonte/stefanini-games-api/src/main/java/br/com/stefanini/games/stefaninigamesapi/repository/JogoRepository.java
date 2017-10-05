package br.com.stefanini.games.stefaninigamesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stefanini.games.stefaninigamesapi.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	

}
