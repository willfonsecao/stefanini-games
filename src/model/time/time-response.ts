import { JogadorResponse } from './../jogador/jogador-response';
import { CampeonatoResponse } from './../campeonato/campeonato-response';
import { JogoResponse } from '../jogo/jogo-response';
export class TimeResponse {

    id: number;
	pontuacao: number;
	jogadores: JogadorResponse[] = [];
	jogosTime1: JogadorResponse[] = [];
	jogosTime2: JogoResponse[] = [];
	jogosVencidos: JogoResponse[] = [];
	jogosPerdidos: JogoResponse[] = [];
    campeonato: CampeonatoResponse;
    
}