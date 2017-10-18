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
			+ " WHERE campeonato.dataInicioInscricoes <= :dataAtual"
			+ " AND campeonato.dataFimInscricoes >= :dataAtual"
			+ " AND campeonato.isJogosGerados = 0"
			+ " AND campeonato.maxInscritos > (SELECT COUNT(time.id) FROM Time time WHERE time.campeonato.id = campeonato.id) ")
	public List<Campeonato> getCampeonatosAbertos(@Param("dataAtual") Date dataAtual);

	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " WHERE campeonato.dataInicioInscricoes > :dataAtual")
	public List<Campeonato> getCampeonatosInscricaoFutura(@Param("dataAtual") Date dataAtual);

	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " WHERE ( campeonato.dataFimInscricoes <= :dataAtual"
			+ " AND campeonato.dataInicio >= :dataAtual )"
			+ " OR campeonato.maxInscritos = (SELECT COUNT(time.id) FROM Time time WHERE time.campeonato.id = campeonato.id)"
			+ " AND campeonato.dataInicio >= :dataAtual "
			+ " AND campeonato.campeao IS NULL ")
	public List<Campeonato> getCampeonatosInscricaoEncerrada(@Param("dataAtual") Date dataAtual);

	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " WHERE campeonato.isJogosGerados = 1"
			+ " AND campeonato.campeao IS NULL ")
	public List<Campeonato> getCampeonatosIniciados();

	@Query(value="select distinct campeonato "
			+ " FROM Campeonato campeonato "
			+ " WHERE campeonato.campeao IS NOT NULL ")
	public List<Campeonato> getCampeonatosFinalizados();
	
}
