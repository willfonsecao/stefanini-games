package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigames.api.dto.response.EtapaDTOResponse;
import br.com.stefanini.games.stefaninigames.api.dto.response.JogoDTOResponse;
import br.com.stefanini.games.stefaninigames.api.dto.response.JogosEtapaDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.model.Jogo;
import br.com.stefanini.games.stefaninigamesapi.repository.EtapaRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.JogoEtapaRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.JogoRepository;

@Service
public class EtapaService {
	
	@Autowired
	private EtapaRepository etapaRepository;

	@Autowired
	private JogoEtapaRepository jogoEtapaRepository;

	@Autowired
	private JogoRepository jogoRepository;
	
	public List<EtapaDTOResponse> getEtapas(Long idCampeonato){
		return etapaRepository.getEtapasCampeonato(idCampeonato).stream()
				.map((etapa) -> new EtapaDTOResponse(etapa))
				.collect(Collectors.toList());
	}
	
	public List<JogosEtapaDTOResponse> getJogosEtapas(Long idEtapa){
		return jogoEtapaRepository.getJogosEtapa(idEtapa).stream()
				.map((jogoEtapa) -> new JogosEtapaDTOResponse(jogoEtapa))
				.collect(Collectors.toList());
	}
	
	public List<JogoDTOResponse> getJogos(Long idJogoEtapa){
		return jogoRepository.getJogosEtapa(idJogoEtapa).stream()
				.map((jogo) -> new JogoDTOResponse(jogo))
				.collect(Collectors.toList());
	}
	
	public JogoDTOResponse saveJogo(Jogo jogo){
		Jogo jogoSalvo = jogoRepository.save(jogo);
		return new JogoDTOResponse(jogoSalvo);
	}
	
	
}
