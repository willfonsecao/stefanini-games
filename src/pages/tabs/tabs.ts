import { Key } from './../../core/infra/local-storage/key';
import { UsuarioResponse } from './../../model/user/usuario';
import { LocalStorageService } from './../../core/infra/local-storage/local-storage-service';
import { Component } from '@angular/core';

import { HomePage } from '../home/home';
import { InscricoesPage } from "../inscricoes/inscricoes";
import { JogadorPage } from "../jogador/jogador";
import { RankingPage } from "../ranking/ranking";
import { AdministracaoPage } from '../administracao/administracao';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  home = HomePage;
  inscricoes = InscricoesPage;
  jogador = JogadorPage;
  ranking = RankingPage;
  adm = AdministracaoPage;

  usuario: UsuarioResponse;

  constructor(private localStorageService: LocalStorageService) {

  }

  isAdm() : boolean{
    this.usuario = this.localStorageService.pull(Key.usuarioLogado);
    if(this.usuario){
      return this.usuario.administrador;
    }
    return false;
  }
}
