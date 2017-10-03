package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.stefanini.games.stefaninigamesapi.model.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {
	
	@Query(value="select distinct time "
			+ " FROM Time time "
			+ " LEFT JOIN FETCH time.campeonato campeonato"
			+ " LEFT JOIN FETCH time.jogadores jogadores"
			+ " WHERE jogadores.usuario.id = :idUsuario "
			+ " AND campeonato.id = :idCampeonato")
	public Time getTime(@Param("idUsuario") Long idUsuario,@Param("idCampeonato") Long idCampeonato);

	@Query(value="select distinct time "
			+ " FROM Time time "
			+ " LEFT JOIN FETCH time.campeonato campeonato"
			+ " WHERE campeonato.id = :idCampeonato ")
	public List<Time> getInscritos(@Param("idCampeonato") Long idCampeonato);

	@Query(value="select distinct time "
			+ " FROM Time time "
			+ " LEFT JOIN FETCH time.campeonato campeonato"
			+ " LEFT JOIN FETCH time.jogadores jogador"
			+ " WHERE jogador.usuario.id = :idUsuario "
			+ " AND campeonato.dataInicioInscricoes <= :dataAtual"
			+ " AND campeonato.dataFimInscricoes >= :dataAtual")
	public List<Time> getCampeonatosInscrito(@Param("idUsuario") Long idUsuario,@Param("dataAtual") Date dataAtual);
	

}
