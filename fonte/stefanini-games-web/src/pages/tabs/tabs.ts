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

  constructor() {

  }
}
