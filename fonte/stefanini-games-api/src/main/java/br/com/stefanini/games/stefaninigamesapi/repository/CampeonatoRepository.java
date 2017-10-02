package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
	
	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " LEFT JOIN FETCH campeonato.inscritos inscritos"
			+ " LEFT JOIN FETCH inscritos.jogadores jogadores"
			+ " WHERE jogadores.usuario.id = :idUsuario "
			+ " AND campeonato.dataInicioInscricoes <= :dataAtual"
			+ " AND campeonato.dataFimInscricoes >= :dataAtual")
	public List<Campeonato> getCampeonatosAbertosInscricao(@Param("idUsuario") Long idUsuario,@Param("dataAtual") Date dataAtual);

	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " WHERE campeonato.dataInicioInscricoes <= :dataAtual"
			+ " AND campeonato.dataFimInscricoes >= :dataAtual")
	public List<Campeonato> getCampeonatosAbertos(@Param("dataAtual") Date dataAtual);

	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " WHERE campeonato.dataFimInscricoes <= :dataAtual"
			+ " AND campeonato.dataInicio >= :dataAtual")
	public List<Campeonato> getCampeonatosInscricaoEncerrada(@Param("dataAtual") Date dataAtual);

	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " WHERE campeonato.dataInicio <= :dataAtual")
	public List<Campeonato> getCampeonatosIniciados(@Param("dataAtual") Date dataAtual);

}
