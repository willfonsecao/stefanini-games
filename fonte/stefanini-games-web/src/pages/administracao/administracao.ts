import { AdministradoresPage } from './administradores/administradores';
import { CampeonatoPage } from './campeonato/campeonato';
import { CategoriaPage } from './categoria/categoria';
import { NavController } from "ionic-angular";
import { Component } from "@angular/core";


@Component({
    selector: 'page-administracao',
    templateUrl: 'administracao.html'
  })
  export class AdministracaoPage {
  
    constructor(public navCtrl: NavController) {
  
    }

    irCategorias(){
      this.navCtrl.push(CategoriaPage);
    }
    
    irCampeonatos(){
      this.navCtrl.push(CampeonatoPage);
    }

    irAdministradores(){
      this.navCtrl.push(AdministradoresPage);
    }
  
  }
  