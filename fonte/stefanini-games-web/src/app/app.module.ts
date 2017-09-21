import { CategoriaPage } from './../pages/administracao/categoria/categoria';
import { CategoriaService } from './../core/service/categoria/categoria-service';
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
import { ExtendedXHRBackend } from '../core/infra/http/extended-xhrbackend';
import { XHRBackend, HttpModule } from '@angular/http';
import { LocalStorageService } from '../core/infra/local-storage/local-storage-service';

@NgModule({
  declarations: [
    MyApp,
    InscricoesPage,
    JogadorPage,
    HomePage,
    RankingPage,
    AdministracaoPage,
    CategoriaPage,
    TabsPage
  ],
  imports: [
    BrowserModule,
    HttpModule,
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
    CategoriaPage,
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    {provide: XHRBackend, useClass: ExtendedXHRBackend},
    LocalStorageService,
    CategoriaService
  ]
})
export class AppModule {}
