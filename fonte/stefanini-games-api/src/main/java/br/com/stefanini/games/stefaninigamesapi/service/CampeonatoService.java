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
	
	public List<Usuario> getInscritos(Long idCampeonato){
		List<Time> times = timeRepository.getInscritos(idCampeonato);
		List<Usuario> usuarios = new ArrayList<>();
		times.stream().forEach((time) 
				-> time.getJogadores().forEach((jogador)
						-> usuarios.add(jogador.getUsuario())) );
		
		return usuarios;
		
	}
	
	public List<CampeonatoDTOResponse> getCampeonatosAbertosInscricao(Long idUsuario, Date dataAtual) {
		List<Time> times = timeRepository.getCampeonatosInscrito(idUsuario, dataAtual);
		List<CampeonatoDTOResponse> camps = new ArrayList<>();
		times.stream().forEach((time) -> camps.add(new CampeonatoDTOResponse(time.getCampeonato())));
		return camps;
	}

	public List<CampeonatoDTOResponse> getCampeonatosAbertos(){
		List<CampeonatoDTOResponse> camps =  this.campeonatoRepository.getCampeonatosAbertos(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
		
		camps.stream().forEach((c)->isGerarJogos(c));
		
		return camps;
	}

	public List<CampeonatoDTOResponse> getCampeonatosInscricaoEncerrada(){
		List<CampeonatoDTOResponse> camps = this.campeonatoRepository.getCampeonatosInscricaoEncerrada(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
		camps.stream().forEach((c)->isGerarJogos(c));
		return camps;
	}

	public List<CampeonatoDTOResponse> getCampeonatosIniciados(){
		List<CampeonatoDTOResponse> camps = this.campeonatoRepository.getCampeonatosIniciados(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
		camps.stream().forEach((c)->isGerarJogos(c));
		return camps;
	}

	public List<CampeonatoDTOResponse> getCampeonatosFinalizados(){
		return this.campeonatoRepository.getCampeonatosFinalizados(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
	}

	public List<CampeonatoDTOResponse> getCampeonatosInscricaoFutura(){
		return this.campeonatoRepository.getCampeonatosInscricaoFutura(new Date())
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
	}
	
	public void cancelarInscricao(Long idUsuario, Long idCampeonato){
		Time time = timeRepository.getTime(idUsuario, idCampeonato);
		timeRepository.delete(time.getId());
	}
	
	public void isGerarJogos(CampeonatoDTOResponse camp) {
		List<Time> inscritos = timeRepository.getInscritos(camp.getId());
		boolean isGerar = (isPeriodoFinalizado(camp.getDataInicioInscricoes(), camp.getDataFimInscricoes())
				&& isTotalInscritosPar(inscritos.size())) || isTotalInscrito(inscritos.size(), camp.getMaxInscritos());
		camp.setGerarJogos(isGerar);
	}
	
	private boolean isPeriodoFinalizado(Date dataInicio, Date dataFim){
		return dataInicio.before(new Date()) && dataFim.before(new Date());
	}
	
	private boolean isTotalInscrito(int totalInscritos, Long maxInscritos){
		return totalInscritos == maxInscritos;
	}
	
	private boolean isTotalInscritosPar(int totalInscritos){
		return totalInscritos % 2 == 0;
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
