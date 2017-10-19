package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.Date;
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

	@Query(value="select distinct jogo "
			+ " FROM Jogo jogo "
			+ " LEFT JOIN FETCH jogo.time1 time1 "
			+ " LEFT JOIN FETCH jogo.time2 time2 "
			+ " LEFT JOIN FETCH time1.campeonato campeonato "
			+ " LEFT JOIN FETCH time1.jogadores jogadores1 "
			+ " LEFT JOIN FETCH time2.jogadores jogadores2 "
			+ " WHERE jogadores1.usuario.id = :idUsuario "
			+ " OR jogadores2.usuario.id = :idUsuario "
			+ " AND jogo.data > :dataAtual ")
	public List<Jogo> getProximosJogosUsuario(@Param("idUsuario") Long idUsuario,@Param("dataAtual") Date dataAtual);
	

}
