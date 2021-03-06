package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.stefanini.games.stefaninigamesapi.model.Etapa;

public interface EtapaRepository extends JpaRepository<Etapa, Long> {
	
	@Query(value="select distinct etapa "
			+ " FROM Etapa etapa "
			+ " LEFT JOIN FETCH etapa.campeonato camp"
			+ " LEFT JOIN FETCH etapa.jogosEtapas jogosEtapas"
			+ " LEFT JOIN FETCH jogosEtapas.jogo jogo"
			+ " WHERE camp.id = :idCampeonato "
			+ " ORDER BY etapa.data DESC ")
	public List<Etapa> getEtapasCampeonato(@Param("idCampeonato") Long idCampeonato);


}
