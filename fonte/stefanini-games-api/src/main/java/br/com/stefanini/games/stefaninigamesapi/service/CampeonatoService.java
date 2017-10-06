package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.games.stefaninigames.api.dto.response.CampeonatoDTOResponse;
import br.com.stefanini.games.stefaninigamesapi.enumarated.EtapasEnum;
import br.com.stefanini.games.stefaninigamesapi.exception.rest.UnprocessableEntityException;
import br.com.stefanini.games.stefaninigamesapi.model.Campeonato;
import br.com.stefanini.games.stefaninigamesapi.model.Etapa;
import br.com.stefanini.games.stefaninigamesapi.model.Jogador;
import br.com.stefanini.games.stefaninigamesapi.model.Jogo;
import br.com.stefanini.games.stefaninigamesapi.model.JogoEtapa;
import br.com.stefanini.games.stefaninigamesapi.model.Time;
import br.com.stefanini.games.stefaninigamesapi.model.Usuario;
import br.com.stefanini.games.stefaninigamesapi.repository.CampeonatoRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.EtapaRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.JogadorRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.JogoEtapaRepository;
import br.com.stefanini.games.stefaninigamesapi.repository.JogoRepository;
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

	@Autowired
	private EtapaRepository etapaRepository;

	@Autowired
	private JogoRepository jogoRepository;

	@Autowired
	private JogoEtapaRepository jogoEtapaRepository;

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
	
	public void gerarJogos(Long idCampeonato) {
		Campeonato camp = campeonatoRepository.findOne(idCampeonato);
		List<Time> inscritos = timeRepository.getInscritos(idCampeonato);
		List<Time> inscritosSorteados = SorteioService.sortear(inscritos);
		List<Etapa> etapasCampeonato = etapaRepository.getEtapasCampeonato(idCampeonato);

		if (etapasCampeonato == null || etapasCampeonato.isEmpty()) {
			Etapa etapa = criarEtapa(camp);
			for (int i = 0; i < inscritos.size(); i++) {
				Jogo jogo = criarJogo(inscritos.get(i),inscritosSorteados.get(i),camp.getDataInicio());
				inscritos.remove(i);
				inscritosSorteados.remove(i);
				criarJogoEtapa(etapa, jogo);
			}
		}
		
		camp.setJogosGerados(true);
		campeonatoRepository.save(camp);
	}

	private void criarJogoEtapa(Etapa etapa, Jogo jogo) {
		try {
			JogoEtapa jogoEtapa = new JogoEtapa();
			jogoEtapa.setEtapa(etapa);
			jogoEtapa.setJogo(jogo);
			jogoEtapa = jogoEtapaRepository.save(jogoEtapa);
		} catch (Exception e) {
			throw new UnprocessableEntityException(
					"Não foi possível gerar os relacionamento Etapa/Jogo " + e.getMessage());
		}
	}

	private Jogo criarJogo(Time time1,Time time2, Date dataJogo) {
		Jogo jogo = new Jogo();
		try {
			jogo.setTime1(time1);
			jogo.setTime2(time2);
			jogo.setData(dataJogo);
			jogo = jogoRepository.save(jogo);

		} catch (Exception e) {
			throw new UnprocessableEntityException("Não foi possível gerar os jogos " + e.getMessage());
		}
		return jogo;
	}

	private Etapa criarEtapa(Campeonato camp) {
		Etapa etapa = new Etapa();
		try {
			etapa.setCampeonato(camp);
			etapa.setData(camp.getDataInicio());
			etapa.setNome(EtapasEnum.ETAPA_1.getDescricao());
			etapa = etapaRepository.save(etapa);
		} catch (Exception e) {
			throw new UnprocessableEntityException("Não foi possível gerar a etapa " + e.getMessage());
		}
		return etapa;
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
		
		if(param.getMaxInscritos()== null){
			erros.add("O máximo de inscritos é obrigatório");
		}

		if (!erros.isEmpty()) {
			throw new UnprocessableEntityException(erros);
		}
	}

}
