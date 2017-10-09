package br.com.stefanini.games.stefaninigamesapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stefanini.games.stefaninigames.api.dto.response.EtapaDTOResponse;
import br.com.stefanini.games.stefaninigames.api.dto.response.JogoDTOResponse;
import br.com.stefanini.games.stefaninigames.api.dto.response.JogosEtapaDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.service.EtapaService;

@CrossOrigin
@RestController
@RequestMapping("/etapas")
public class EtapaController {
	
	@Autowired
	private EtapaService etapaService;
	
	@GetMapping(path = "/{idCampeonato}")
	public List<EtapaDTOResponse> getEtapas(@PathVariable("idCampeonato") Long idCampeonato){
		return etapaService.getEtapas(idCampeonato);
	}

	@GetMapping(path = "/jogos-etapas/{idEtapa}")
	public List<JogosEtapaDTOResponse> getJogosEtapas(@PathVariable("idEtapa") Long idEtapa){
		return etapaService.getJogosEtapas(idEtapa);
	}

	@GetMapping(path = "/jogos/{idJogoEtapa}")
	public List<JogoDTOResponse> getJogos(@PathVariable("idJogoEtapa") Long idJogoEtapa){
		return etapaService.getJogos(idJogoEtapa);
	}
	

}
