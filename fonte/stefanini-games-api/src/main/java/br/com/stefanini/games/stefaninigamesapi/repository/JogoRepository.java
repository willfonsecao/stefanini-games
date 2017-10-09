package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.stefanini.games.stefaninigamesapi.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	@Query(value="select distinct jogo "
			+ " FROM Jogo jogo "
			+ " LEFT JOIN FETCH jogo.jogosEtapas jogosEtapas"
			+ " WHERE jogosEtapas.id = :idJogoEtapa ")
	public List<Jogo> getJogosEtapa(@Param("idJogoEtapa") Long idJogoEtapa);
	

}
