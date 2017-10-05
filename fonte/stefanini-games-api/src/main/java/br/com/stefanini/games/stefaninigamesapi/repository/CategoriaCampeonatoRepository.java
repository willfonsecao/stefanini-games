package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.stefanini.games.stefaninigamesapi.model.CategoriaCampeonato;

public interface CategoriaCampeonatoRepository extends JpaRepository<CategoriaCampeonato, Long> {
	
	
	@Query(value="select distinct categoria "
			+ " FROM CategoriaCampeonato categoria "
			+ " LEFT JOIN FETCH categoria.campeonatos camps"
			+ " WHERE camps.dataInicioInscricoes <= :dataAtual "
			+ " AND camps.dataFimInscricoes >= :dataAtual "
			+ " AND camps.isJogosGerados = 0 ")
	public List<CategoriaCampeonato> getCategoriasAbertasParaInscricao(@Param("dataAtual") Date dataAtual);

}
