package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.stefanini.games.stefaninigamesapi.model.JogoEtapa;

public interface JogoEtapaRepository extends JpaRepository<JogoEtapa, Long> {
	
	@Query(value="select distinct jogoEtapa "
			+ " FROM JogoEtapa jogoEtapa "
			+ " LEFT JOIN FETCH jogoEtapa.etapa etapa"
			+ " LEFT JOIN FETCH jogoEtapa.jogo jogo"
			+ " WHERE etapa.id = :idEtapa ")
	public List<JogoEtapa> getJogosEtapa(@Param("idEtapa") Long idEtapa);

	@Query(value="select distinct jogoEtapa "
			+ " FROM JogoEtapa jogoEtapa "
			+ " LEFT JOIN FETCH jogoEtapa.etapa etapa"
			+ " LEFT JOIN FETCH etapa.campeonato campeonato"
			+ " LEFT JOIN FETCH jogoEtapa.jogo jogo"
			+ " WHERE jogo.id = :idJogo ")
	public JogoEtapa getJogosEtapaByJogo(@Param("idJogo") Long idJogo);

}
