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
			+ " WHERE camp.id = :idCampeonato ")
	public List<Etapa> getEtapasCampeonato(@Param("idCampeonato") Long idCampeonato);
	
	
}
