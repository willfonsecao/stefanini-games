import { UsuarioResponse } from './../../model/user/usuario';
import { Key } from './../../core/infra/local-storage/key';
import { LocalStorageService } from './../../core/infra/local-storage/local-storage-service';
import { CampeonatoService } from './../../core/service/campeonato/campeonato-service';
import { VisualizarCampeonatoPage } from './visualizar-campeonato/visualizar-campeonato';
import { CampeonatoResponse } from './../../model/campeonato/campeonato-response';
import { DataUtil } from './../../util/data-util';
import { CategoriaResponse } from './../../model/categoria/categoria-response';
import { CategoriaService } from './../../core/service/categoria/categoria-service';
import { Component, OnInit } from "@angular/core";
import { NavController } from "ionic-angular";

@Component({
    selector: 'page-inscricoes',
    templateUrl: 'inscricoes.html'
  })
  export class InscricoesPage implements OnInit {

    categorias: CategoriaResponse[] = [];
    campeonatosInscrito: CampeonatoResponse[] = [];
    isUsuarioInscrito: boolean;  
      
    constructor(public navCtrl: NavController,
      private categoriaService: CategoriaService,
      private campeonatoService: CampeonatoService,
      private localStorageService: LocalStorageService) {

    }

    ngOnInit(): void {
      this.buscarAbertas();
    }
    
    buscarAbertas(){
      this.categoriaService.buscarAbertas(c => {
        this.categorias = c
        this.getCampeonatosInscritos();
      });
    }

    visualizar(campeonato: CampeonatoResponse, categoria: CategoriaResponse){
      campeonato.categoria = categoria;
      this.navCtrl.push(VisualizarCampeonatoPage, campeonato);
    }

    inscrever(camp:CampeonatoResponse){
      let usuario = this.getUsuario();
      camp.isInscrito = true;
      this.campeonatoService.inscrever(usuario.id,camp.id,(value)=>{
        this.buscarAbertas();
      });
    }

    cancelarInscricao(camp:CampeonatoResponse){
      let usuario = this.getUsuario();
      camp.isInscrito = false;
      this.campeonatoService.cancelarInscricao(usuario.id,camp.id,(value)=>{
      });
    }

    getCampeonatosInscritos(){
      let usuario = this.getUsuario();
      this.campeonatoService.getCampeonatosInscrito(usuario.id,(campeonatos)=>{
        this.campeonatosInscrito = campeonatos;
        this.categorias.forEach((cat) => {
          cat.campeonatos.forEach((camp) => {
            this.atualizarInscrito(camp);
          })
        })
      });
    }

    getLogo(categoria: CategoriaResponse){
       return 'data:image/jpg;base64,' + categoria.logo;
    }

    atualizarInscrito(campAberto: CampeonatoResponse){
      
      if(this.campeonatosInscrito) {
        for (var index = 0; index < this.campeonatosInscrito.length; index++) {
          var camp = this.campeonatosInscrito[index];
          if (campAberto.id == camp.id) {
            campAberto.isInscrito = true;
          }else{
            campAberto.isInscrito = false;
          }
        }
      }else{
        campAberto.isInscrito = false;
      }
      
    }

    getDataFormatada(data: Date){
      return DataUtil.getDataFormatada(data);
    }

    getUsuario(): UsuarioResponse{
      let usuario: UsuarioResponse = this.localStorageService.pull(Key.usuarioLogado);
      return usuario;
    }

}