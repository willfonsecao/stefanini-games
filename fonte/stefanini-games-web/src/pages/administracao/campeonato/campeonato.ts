import { CadastrarCampeonatoPage } from './cadastro-campeonato/cadastrar-campeonato';
import { Component, OnInit } from "@angular/core";
import { NavController } from "ionic-angular";

@Component({
    selector: 'page-campeonato',
    templateUrl: 'campeonato.html'
  })
  export class CampeonatoPage implements OnInit{

    constructor(public navCtrl: NavController) {

    }
    
    ngOnInit(): void {

    }

    irCadastrarCampeonatos(){
      this.navCtrl.push(CadastrarCampeonatoPage);
    }
}