package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigames.api.dto.response.CampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.exception.rest.UnprocessableEntityException;
import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.model.Jogador;
import br.com.stefanini.games.stefaninigamesapi.model.Time;
import br.com.stefanini.games.stefaninigamesapi.model.Usuario;
import br.com.stefanini.games.stefaninigamesapi.repository.CampeonatoRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.JogadorRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.TimeRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.UsuarioRepository;

@Service
public class CampeonatoService {
	
	@Autowired
	private CampeonatoRepository campeonatoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TimeRepository timeRepository;

	@Autowired
	private JogadorRepository jogadorRepository;
	
	public List<CampeonatoDTOResponse> findAll(){
		return this.campeonatoRepository.findAll()
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
	}
	
	public Campeonato save(Campeonato campeonato){
		this.validarCampeonato(campeonato);
		return this.campeonatoRepository.save(campeonato);
	}
	
	public void inscrever(Long idUsuario, Long idCampeonato){
		Campeonato campeonato = campeonatoRepository.findOne(idCampeonato);
		Usuario usuario = usuarioRepository.findOne(idUsuario);
		Time time = criarTime(campeonato);
		criarJogador(usuario, time);
	}
	
	public List<CampeonatoDTOResponse> getCampeonatosAbertosInscricao(Long idUsuario, Date dataAtual){
		 return this.campeonatoRepository.getCampeonatosAbertosInscricao(idUsuario, dataAtual)
					.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
					.collect(Collectors.toList());
	}

	public List<CampeonatoDTOResponse> getCampeonatosAbertos(){
		return this.campeonatoRepository.getCampeonatosAbertos(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
	}

	public List<CampeonatoDTOResponse> getCampeonatosInscricaoEncerrada(){
		return this.campeonatoRepository.getCampeonatosInscricaoEncerrada(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
	}

	public List<CampeonatoDTOResponse> getCampeonatosIniciados(){
		return this.campeonatoRepository.getCampeonatosIniciados(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
	}
	
	public void cancelarInscricao(Long idUsuario, Long idCampeonato){
		Time time = timeRepository.getTime(idUsuario, idCampeonato);
		timeRepository.delete(time.getId());
	}

	private void criarJogador(Usuario usuario, Time time) {
		Jogador jogador = new Jogador();
		jogador.setUsuario(usuario);
		jogador.setTime(time);
		jogadorRepository.save(jogador);
	}

	private Time criarTime(Campeonato campeonato) {
		Time time = new Time();
		time.setCampeonato(campeonato);
		time = timeRepository.save(time);
		return time;
	}
	
	public void delete(Long id){
		this.campeonatoRepository.delete(id);
	}
	
	private void validarCampeonato(Campeonato param){
		List<String> erros = new ArrayList<>();

		if (param.getCategoria().getId() == null) {
			erros.add("O campo Categoria é obrigatório.");
		}

		if (!erros.isEmpty()) {
			throw new UnprocessableEntityException(erros);
		}
	}

}
