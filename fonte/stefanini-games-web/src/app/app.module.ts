import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { InscricoesPage } from "../pages/inscricoes/inscricoes";
import { JogadorPage } from "../pages/jogador/jogador";
import { RankingPage } from "../pages/ranking/ranking";
import { AdministracaoPage } from '../pages/administracao/administracao';

@NgModule({
  declarations: [
    MyApp,
    InscricoesPage,
    JogadorPage,
    HomePage,
    RankingPage,
    AdministracaoPage,
    TabsPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    InscricoesPage,
    JogadorPage,
    HomePage,
    RankingPage,
    AdministracaoPage,
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
