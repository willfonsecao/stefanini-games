package br.com.stefanini.games.stefaninigamesapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stefanini.games.stefaninigames.api.dto.response.CampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigames.api.dto.response.JogoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.model.Usuario;
import br.com.stefanini.games.stefaninigamesapi.service.CampeonatoService;

@CrossOrigin
@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
	
	@Autowired
	private CampeonatoService campeonatoService;
	
	@GetMapping
	public List<CampeonatoDTOResponse> getCampeonatos(){
		return this.campeonatoService.findAll();
	}

	@GetMapping(path = "/get-one/{idCampeonato}")
	public CampeonatoDTOResponse getById(@PathVariable Long idCampeonato){
		return campeonatoService.getById(idCampeonato);
	}

	@GetMapping(path = "/campeoes")
	public List<Object> getCampeoes(){
		return campeonatoService.getCampeoes();
	}

	@GetMapping(path = "/proximos-jogos/{idUsuario}")
	public List<JogoDTOResponse> getProximoxJogosUsuario(@PathVariable Long idUsuario){
		return campeonatoService.getProximoxJogosUsuario(idUsuario);
	}
	
	@PostMapping
	public Campeonato save(@RequestBody Campeonato campeonato){
		return this.campeonatoService.save(campeonato);
	}
	
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable Long id){
		this.campeonatoService.delete(id);
	}

	@DeleteMapping(path = "/{idCampeonato}/inscricao/{idUsuario}")
	public void cancelarInscricao(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idCampeonato") Long idCampeonato){
		this.campeonatoService.cancelarInscricao(idUsuario,idCampeonato);
	}
	
	@PostMapping(path = "/{idCampeonato}/inscricao/{idUsuario}")
	public void inscrever(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idCampeonato") Long idCampeonato){
		this.campeonatoService.inscrever(idUsuario,idCampeonato);
	}

	@PostMapping(path = "/gerar-jogos/{idCampeonato}")
	public void gerarJogos(@PathVariable("idCampeonato") Long idCampeonato){
		campeonatoService.gerarJogos(idCampeonato);
		
	}

	@GetMapping(path = "/inscricao/{idUsuario}")
	public List<CampeonatoDTOResponse> getCampeonatosAbertosInscricao(@PathVariable("idUsuario") Long idUsuario){
		return this.campeonatoService.getCampeonatosAbertosInscricao(idUsuario,new Date());
	}

	@GetMapping(path = "/inscritos/{idCampeonato}")
	public List<Usuario> getInscritos(@PathVariable("idCampeonato") Long idCampeonato){
		return this.campeonatoService.getInscritos(idCampeonato);
	}

	@GetMapping(path = "/abertos")
	public List<CampeonatoDTOResponse> getCampeonatosAbertos(){
		return this.campeonatoService.getCampeonatosAbertos();
	}

	@GetMapping(path = "/fechados")
	public List<CampeonatoDTOResponse> getCampeonatosInscricaoEncerrada(){
		return this.campeonatoService.getCampeonatosInscricaoEncerrada();
	}

	@GetMapping(path = "/iniciados")
	public List<CampeonatoDTOResponse> getCampeonatosIniciados(){
		return this.campeonatoService.getCampeonatosIniciados();
	}

	@GetMapping(path = "/finalizados")
	public List<CampeonatoDTOResponse> getCampeonatosFinalizados(){
		return this.campeonatoService.getCampeonatosFinalizados();
	}

	@GetMapping(path = "/futuros")
	public List<CampeonatoDTOResponse> getCampeonatosInscricaoFutura(){
		return this.campeonatoService.getCampeonatosInscricaoFutura();
	}

}
