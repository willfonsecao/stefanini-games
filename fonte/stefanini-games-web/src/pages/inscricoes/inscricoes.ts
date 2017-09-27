import { VisualizarCampeonatoPage } from './visualizar-campeonato/visualizar-campeonato';
import { CampeonatoResponse } from './../../model/campeonato/campeonato-response';
import { DataUtil } from './../../util/data-util';
import { CategoriaResponse } from './../../model/categoria/categoria-response';
import { CategoriaService } from './../../core/service/categoria/categoria-service';
import { Component, OnInit } from "@angular/core";
import { NavController } from "ionic-angular";
import * as moment from 'moment';


@Component({
    selector: 'page-inscricoes',
    templateUrl: 'inscricoes.html'
  })
  export class InscricoesPage implements OnInit {

    categorias: CategoriaResponse[];  
      
    constructor(public navCtrl: NavController,
      private categoriaService: CategoriaService) {

    }

    ngOnInit(): void {
      this.buscarAbertas();
    }

    buscarAbertas(){
      this.categoriaService.buscarAbertas(c => {this.categorias = c});
    }

    visualizar(campeonato: CampeonatoResponse, categoria: CategoriaResponse){
      campeonato.categoria = categoria;
      this.navCtrl.push(VisualizarCampeonatoPage, campeonato);
    }

    inscrever(campeonato: CampeonatoResponse){

    }

    getDataFormatada(data: Date){
      return DataUtil.getDataFormatada(data);
    }

}