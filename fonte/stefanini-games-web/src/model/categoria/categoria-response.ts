import { CampeonatoResponse } from './../campeonato/campeonato-response';

export class CategoriaResponse {
    
       id: number;
       nome: string;
       campeonatos: CampeonatoResponse[] = [];
       logo: string;
   }