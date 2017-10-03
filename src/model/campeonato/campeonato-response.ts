import { TimeResponse } from './../time/time-response';
import { CategoriaResponse } from './../categoria/categoria-response';

export class CampeonatoResponse {

    id: number;
    dataInicio: Date = new Date();
    dataFim: Date;
    dataInicioInscricoes: Date;
    dataFimInscricoes: Date;
    categoria: CategoriaResponse = new CategoriaResponse();
    premioCampeao: string;
    premioSegundoColocado: string;
    premioTerceiroColocado: string;
    edicao: string;
    inscritos: TimeResponse[] = [];
    isInscrito: boolean;
    maxInscritos: number;
    isGerarJogos: boolean;
    isVagasPreenchidas: boolean;

}