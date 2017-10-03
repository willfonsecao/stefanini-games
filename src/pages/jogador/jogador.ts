import { EditarPerfilPage } from './editar-perfil/editar-perfil';
import { LoginPage } from './../login/login';
import { UsuarioResponse } from './../../model/user/usuario';
import { LocalStorageService } from './../../core/infra/local-storage/local-storage-service';
import { Component } from "@angular/core";
import { NavController, App } from "ionic-angular";
import { Key } from '../../core/infra/local-storage/key';

@Component({
    selector: 'page-jogador',
    templateUrl: 'jogador.html'
  })
  export class JogadorPage {

    usuario: UsuarioResponse;
  
    constructor(public navCtrl: NavController,
                private localStorageService: LocalStorageService,
                private app: App) {
  
    }

    getUsuarioName(){
      this.usuario = this.localStorageService.pull(Key.usuarioLogado);
      if(this.usuario){
        return this.usuario.nome;
      }

      return '';
    }

    editarPerfil(){
      this.navCtrl.push(EditarPerfilPage,{usuario: this.usuario});
    }

    logout(){
      this.localStorageService.clearAll();
      let nav: NavController = this.app.getRootNav();
      nav.setRoot(LoginPage);
    }
  
  }