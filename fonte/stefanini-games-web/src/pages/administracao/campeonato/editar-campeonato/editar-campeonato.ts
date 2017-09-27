import { CategoriaResponse } from './../../../../model/categoria/categoria-response';
import { CategoriaService } from './../../../../core/service/categoria/categoria-service';
import { CampeonatoResponse } from './../../../../model/campeonato/campeonato-response';
import { Component, OnInit } from "@angular/core";
import { ViewController, NavParams } from 'ionic-angular';
import * as moment from 'moment';

@Component({
    selector: 'page-editar-campeonato',
    templateUrl: 'editar-campeonato.html'
  })
  export class EditarCampeonatoPage implements OnInit{

    nomeMeses: string[] = ['janeiro, fevereiro, mar\u00e7o, abril, maio, junho, julho, agosto, setembro, outubro, novembro, dezembro']
    siglaMeses: string[] = ['Jan, Fev, Mar, Abr, Mai, Jun, Jul, Ago, Set, Out, Nov, Dez'];
    dataInicio: string;
    dataFim: string;
    dataInicioInscricao: string;
    dataFimInscricao: string;
    campeonato: CampeonatoResponse;
    categorias: CategoriaResponse[];
    minIncricoesInicio: string;
    minIncricoesFim: string;
    minDataInicio: string;
    minDataFim: string;

    constructor(private params: NavParams,
                private categoriaService: CategoriaService){

        this.campeonato = this.params.get('campeonato');
        
        if(this.campeonato){
            this.popularDatas();
            this.popularMinDates();
        }
    }
    
    ngOnInit(): void {
        this.buscar();
    }
    
    buscar(){
        this.categoriaService.buscar(c => {this.categorias = c})
    }

    popularDatas() {
        this.dataInicio = moment(this.campeonato.dataInicio).toDate().toISOString();
        this.dataFim = moment(this.campeonato.dataFim).toDate().toISOString();
        this.dataInicioInscricao = moment(this.campeonato.dataInicioInscricoes).toDate().toISOString();
        this.dataFimInscricao = moment(this.campeonato.dataFimInscricoes).toDate().toISOString();
    }

    popularMinDates() {
        this.minIncricoesInicio = moment(this.campeonato.dataInicioInscricoes).format('YYYY-MM-DD');
        this.minIncricoesFim = moment(this.campeonato.dataFimInscricoes).format('YYYY-MM-DD');
        this.minDataInicio = moment(this.campeonato.dataInicio).format('YYYY-MM-DD');
        this.minDataFim = moment(this.campeonato.dataFim).format('YYYY-MM-DD');
    }

  }