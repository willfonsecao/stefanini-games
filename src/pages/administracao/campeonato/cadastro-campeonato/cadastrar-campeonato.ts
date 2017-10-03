import { Component, OnInit } from "@angular/core";
import { NavController } from "ionic-angular";
import { CategoriaService } from '../../../../core/service/categoria/categoria-service';
import { CategoriaResponse } from '../../../../model/categoria/categoria-response';
import * as moment from 'moment';
import { CampeonatoResponse } from "../../../../model/campeonato/campeonato-response";
import { CampeonatoService } from "../../../../core/service/campeonato/campeonato-service";
import { CampeonatoPage } from "../campeonato";


@Component({
    selector: 'page-cadastrar-campeonato',
    templateUrl: 'cadastrar-campeonato.html'
  })
  export class CadastrarCampeonatoPage implements OnInit{
    nomeMeses: string[] = ['janeiro, fevereiro, mar\u00e7o, abril, maio, junho, julho, agosto, setembro, outubro, novembro, dezembro']
    siglaMeses: string[] = ['Jan, Fev, Mar, Abr, Mai, Jun, Jul, Ago, Set, Out, Nov, Dez']
    categorias: CategoriaResponse[];
    campeonato: CampeonatoResponse = new CampeonatoResponse();
    inscricoesInicio: string = new Date().toISOString();
    inscricoesFim: string = moment().add('days', 3).toDate().toISOString();
    dataInicio: string = moment().add('days', 7).toDate().toISOString();
    dataFim: string = moment().add('days', 8).toDate().toISOString();
    minIncricoesInicio: string = moment().format('YYYY-MM-DD');
    minIncricoesFim: string = moment(Date.parse(this.inscricoesFim)).format('YYYY-MM-DD');
    minDataInicio: string = moment(Date.parse(this.dataInicio)).format('YYYY-MM-DD');
    minDataFim: string = moment(Date.parse(this.dataFim)).format('YYYY-MM-DD');

    constructor(public navCtrl: NavController,
        private categoriaService: CategoriaService,
        private campeonatoService: CampeonatoService) {

    }
    
    ngOnInit(): void {
        this.buscarCategorias();
    }

    buscarCategorias() {
        this.categoriaService.buscar(c => { this.categorias = c });
    }

    salvar(){
        this.popularCampeonato();
        this.campeonatoService.cadastrar(this.campeonato,()=>{
            this.navCtrl.push(CampeonatoPage);
        });
    }

    popularCampeonato(){
        this.campeonato.dataInicio = new Date(Date.parse(this.dataInicio));
        this.campeonato.dataFim = new Date(Date.parse(this.dataFim));
        this.campeonato.dataInicioInscricoes = new Date(Date.parse(this.inscricoesInicio));
        this.campeonato.dataFimInscricoes = new Date(Date.parse(this.inscricoesFim));
    }
}