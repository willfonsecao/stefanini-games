package br.com.stefanini.games.stefaninigamesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.stefanini.games.stefaninigamesapi.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
	
	@Query(value=" SELECT COUNT(jogador.id) as vitorias, usuario.id, usuario.nome FROM Jogador jogador "
			+ " INNER JOIN jogador.time time"
			+ " INNER JOIN jogador.usuario usuario "
			+ " INNER JOIN time.campeonato campeonato "
			+ " WHERE campeonato.campeao.id = time.id "
			+ " GROUP BY usuario.id, usuario.nome "
			+ " ORDER BY vitorias DESC ")
	public List<Object> getJogadoresCampeoes();

}
