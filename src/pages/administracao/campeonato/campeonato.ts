import { EditarCampeonatoPage } from './editar-campeonato/editar-campeonato';
import { CampeonatoService } from './../../../core/service/campeonato/campeonato-service';
import { CampeonatoResponse } from './../../../model/campeonato/campeonato-response';
import { CadastrarCampeonatoPage } from './cadastro-campeonato/cadastrar-campeonato';
import { Component, OnInit } from "@angular/core";
import { NavController} from "ionic-angular";

@Component({
    selector: 'page-campeonato',
    templateUrl: 'campeonato.html'
  })
  export class CampeonatoPage implements OnInit{

    campeonatos: CampeonatoResponse[] = [];
    campeonatosAbertos: CampeonatoResponse[] = [];
    campeonatosIniciados: CampeonatoResponse[] = [];

    constructor(public navCtrl: NavController,
                private campeonatoService: CampeonatoService) {

    }
    
    ngOnInit(): void {
      this.buscarFechados();
      this.buscarAbertos();
      this.buscarIniciados();
    }

    buscarFechados(){
      this.campeonatoService.getCampeonatosFechados(c => { this.campeonatos = c})
    }

    buscarAbertos(){
      this.campeonatoService.getCampeonatosAbertos(campeonatos =>{
          this.campeonatosAbertos = campeonatos;
      })
    }

    buscarIniciados(){
      this.campeonatoService.getCampeonatosIniciados(iniciados =>{
        this.campeonatosIniciados = iniciados;
      })
    }

    editar(campeonato){
      this.navCtrl.push(EditarCampeonatoPage,campeonato);
    }

    excluir(item: CampeonatoResponse){
      this.campeonatoService.deletar(item.id, 
        () => {
          this.campeonatos.splice(this.campeonatos.indexOf(item), 1);
        });
    }

    irCadastrarCampeonatos(){
      this.navCtrl.push(CadastrarCampeonatoPage);
    }

}