package br.com.stefanini.games.stefaninigamesapi.repository;

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
	

}
