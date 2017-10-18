package br.com.stefanini.games.stefaninigamesapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
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
	
	public CampeonatoDTOResponse getById(Long id){
		Campeonato camp = campeonatoRepository.getOne(id);
		
		if(camp != null){
			return new CampeonatoDTOResponse(camp);
		}
		
		return null;
	}
	
	public Campeonato save(Campeonato campeonato){
		this.validarCampeonato(campeonato);
		return this.campeonatoRepository.save(campeonato);
	}
	
	public List<Object> getCampeoes(){
		return jogadorRepository.getJogadoresCampeoes();
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
		List<CampeonatoDTOResponse> camps = this.campeonatoRepository.getCampeonatosIniciados()
				.stream().map((campeonato) -> new CampeonatoDTOResponse(campeonato))
				.collect(Collectors.toList());
		camps.stream().forEach((c)->isGerarJogos(c));
		return camps;
	}

	public List<CampeonatoDTOResponse> getCampeonatosFinalizados(){
		return this.campeonatoRepository.getCampeonatosFinalizados()
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
		List<Etapa> etapasCampeonato = etapaRepository.getEtapasCampeonato(idCampeonato);

		if (!hasEtapas(etapasCampeonato)) {
			List<Time> inscritos = timeRepository.getInscritos(idCampeonato);
			String nomeEtapa = getNomeNovaEtapa(etapasCampeonato, inscritos.size(), false, false);
			criarEstruturaJogo(camp, nomeEtapa, camp.getDataInicio(), inscritos);

		} else {
			Etapa ultimaEtapa = etapasCampeonato.get(0);
			List<Time> jogadores = this.getVencedoresEtapa(ultimaEtapa);
			Date dataProximaEtapa = new DateTime(ultimaEtapa.getData()).plusDays(7).toDate();

			if (!isProximaEtapaFinal(jogadores.size())) {

				String nomeEtapa = getNomeNovaEtapa(etapasCampeonato, jogadores.size(), false, false);
				criarEstruturaJogo(camp, nomeEtapa, dataProximaEtapa, jogadores);

			} else {

				String nomeFinal = getNomeNovaEtapa(etapasCampeonato, 0, true, false);
				criarEstruturaJogo(camp, nomeFinal, dataProximaEtapa, jogadores);

				List<Time> perdedores = this.getPerdedoresEtapa(ultimaEtapa);
				String nomeTerceiro = getNomeNovaEtapa(etapasCampeonato, 0, false, true);
				criarEstruturaJogo(camp, nomeTerceiro, dataProximaEtapa, perdedores);
			}
		}

		camp.setJogosGerados(true);
		campeonatoRepository.save(camp);
	}
	
	private boolean hasEtapas(List<Etapa> etapasCampeonato) {
		return etapasCampeonato != null && !etapasCampeonato.isEmpty();
	}
	
	private void criarEstruturaJogo(Campeonato camp, String nomeEtapa, Date dataProximaEtapa, List<Time> participantes){
		Etapa etapa = criarEtapa(camp, nomeEtapa, dataProximaEtapa);
		criarEstruturaJogoEtapa(etapa, participantes);
	}
	
	private void criarEstruturaJogoEtapa(Etapa etapa, List<Time> participantes){
		List<Time> participantesSorteados = SorteioService.sortear(participantes);
		
		for (int i = 0; i < participantes.size(); i++) {
			Jogo jogo = criarJogo(participantes.get(i), participantesSorteados.get(i), etapa.getData());
			participantes.remove(i);
			participantesSorteados.remove(i);
			criarJogoEtapa(etapa, jogo);
		}
		
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
	
	private boolean isProximaEtapaFinal(Integer totalVencedores){
		if(totalVencedores == 2){
			return true;
		}
		return false;
	}
	
	private String getNomeNovaEtapa(List<Etapa> etapasCampeonato, Integer totalJogadores, boolean isFinal, boolean isTerceiroLugar){
		EtapasEnum ultimaEtapaEnum;
		if(hasEtapas(etapasCampeonato)){
			ultimaEtapaEnum = EtapasEnum.getEtapa(etapasCampeonato.get(0).getNome());
		}else{
			return EtapasEnum.ETAPA_1.getDescricao();
		}
		
		if(isFinal){
			return EtapasEnum.FINAL.getDescricao();
		}else if(isTerceiroLugar){
			return EtapasEnum.TERCEIRO_LUGAR.getDescricao();
		}
		
		if(totalJogadores >= 16){
			return EtapasEnum.getEtapa(ultimaEtapaEnum.getId() + 1).getDescricao();
		}else if(totalJogadores == 16){
			return EtapasEnum.OITAVAS.getDescricao();
		}else if(totalJogadores == 8){
			return EtapasEnum.QUARTAS.getDescricao();
		}else if(totalJogadores == 4){
			return EtapasEnum.SEMI_FINAL.getDescricao();
		}
		
		return "";
	}
	
	private List<Time> getVencedoresEtapa(Etapa etapa){
		List<Time> vencedores = new ArrayList<>();
		etapa.getJogosEtapas().stream().forEach((jogoEtapa) -> {
			vencedores.add(jogoEtapa.getJogo().getVencedor());
		});
		return vencedores;
	}

	private List<Time> getPerdedoresEtapa(Etapa etapa){
		List<Time> perdedores = new ArrayList<>();
		etapa.getJogosEtapas().stream().forEach((jogoEtapa) -> {
			perdedores.add(jogoEtapa.getJogo().getPerdedor());
		});
		return perdedores;
	}

	private Etapa criarEtapa(Campeonato camp, String nomeEtapa, Date dataEtapa) {
		Etapa etapa = new Etapa();
		try {
			etapa.setCampeonato(camp);
			etapa.setData(dataEtapa);
			etapa.setNome(nomeEtapa);
			etapa = etapaRepository.save(etapa);
		} catch (Exception e) {
			throw new UnprocessableEntityException("Não foi possível gerar a etapa " + e.getMessage());
		}
		return etapa;
	}
	
	public void isGerarJogos(CampeonatoDTOResponse camp) {
		List<Time> inscritos = timeRepository.getInscritos(camp.getId());
		boolean isGerar = !camp.isJogosGerados() || isUltimosJogosComResultados(camp.getId()) && ( (isPeriodoFinalizado(camp.getDataInicioInscricoes(), camp.getDataFimInscricoes())
				&& isTotalInscritosPar(inscritos.size())) || isTotalInscrito(inscritos.size(), camp.getMaxInscritos()) );
		camp.setGerarJogos(isGerar);
	}
	
	private boolean isUltimosJogosComResultados(Long idCampeonato){
		Etapa etapa = etapaRepository.getEtapasCampeonato(idCampeonato).get(0);
		
		for (JogoEtapa jogoEtapa : etapa.getJogosEtapas()) {
			if(jogoEtapa.getJogo().getPlacarTime1() == null || jogoEtapa.getEtapa().getNome().equals(EtapasEnum.FINAL.getDescricao())){
				return false;
			}
		}
		return true;
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
