import { UsuarioService } from './../core/service/user/user-service';
import { LoginPage } from './../pages/login/login';
import { CampeonatoPage } from './../pages/administracao/campeonato/campeonato';
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
import { CadastrarCampeonatoPage } from '../pages/administracao/campeonato/cadastro-campeonato/cadastrar-campeonato';
import { CampeonatoService } from '../core/service/campeonato/campeonato-service';
import { EditarCampeonatoPage } from '../pages/administracao/campeonato/editar-campeonato/editar-campeonato';
import { VisualizarCampeonatoPage } from '../pages/inscricoes/visualizar-campeonato/visualizar-campeonato';
import { LdapService } from '../core/service/login/ldap-Service';
import { EditarPerfilPage } from '../pages/jogador/editar-perfil/editar-perfil';
import { Camera } from '@ionic-native/camera';
import { FileTransfer } from '@ionic-native/file-transfer';

@NgModule({
  declarations: [
    MyApp,
    InscricoesPage,
    JogadorPage,
    HomePage,
    RankingPage,
    AdministracaoPage,
    CategoriaPage,
    CampeonatoPage,
    CadastrarCampeonatoPage,
    EditarCampeonatoPage,
    VisualizarCampeonatoPage,
    EditarPerfilPage,
    LoginPage,
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
    CampeonatoPage,
    CadastrarCampeonatoPage,
    EditarCampeonatoPage,
    VisualizarCampeonatoPage,
    EditarPerfilPage,
    LoginPage,
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    Camera,
    FileTransfer,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    {provide: XHRBackend, useClass: ExtendedXHRBackend},
    LocalStorageService,
    CategoriaService,
    CampeonatoService,
    UsuarioService,
    LdapService
  ]
})
export class AppModule {}
