import { EditarCampeonatoPage } from './editar-campeonato/editar-campeonato';
import { CampeonatoService } from './../../../core/service/campeonato/campeonato-service';
import { CampeonatoResponse } from './../../../model/campeonato/campeonato-response';
import { CadastrarCampeonatoPage } from './cadastro-campeonato/cadastrar-campeonato';
import { Component, OnInit } from "@angular/core";
import { NavController, ModalController } from "ionic-angular";

@Component({
    selector: 'page-campeonato',
    templateUrl: 'campeonato.html'
  })
  export class CampeonatoPage implements OnInit{

    campeonatos: CampeonatoResponse[];

    constructor(public navCtrl: NavController,
                private campeonatoService: CampeonatoService) {

    }
    
    ngOnInit(): void {
      this.buscar()
    }

    buscar(){
      this.campeonatoService.buscar(c => { this.campeonatos = c})
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