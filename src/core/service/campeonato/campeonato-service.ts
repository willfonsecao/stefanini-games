import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { ToastController, LoadingController } from "ionic-angular";
import { HttpConnectionBuilder } from "../../infra/http/http-connection-builder";
import { CampeonatoResponse } from "../../../model/campeonato/campeonato-response";

@Injectable()
export class CampeonatoService {

    constructor(private http: Http,
        private toastCtrl: ToastController,
        private loadingCtrl: LoadingController) {

    }

    cadastrar(campeonato: CampeonatoResponse, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos')
            .addParameter(campeonato)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .generateParametersJson()
            .showToastSucess()
            .buildPost();
    }
    
    inscrever(idUsuario: number,idCampeonato: number,handlerSucess: (value:any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint(`campeonatos/${idCampeonato}/inscricao/${idUsuario}`)
            .addToastrUtil(this.toastCtrl)
            .generateParametersJson()
            .showToastSucess()
            .buildPost();
    }
    
    cancelarInscricao(idUsuario: number,idCampeonato: number,handlerSucess: (value:any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint(`campeonatos/${idCampeonato}/inscricao/${idUsuario}`)
            .addToastrUtil(this.toastCtrl)
            .generateParametersJson()
            .showToastSucess()
            .buildDelete();
    }

    buscar(handlerSucess: (value: CampeonatoResponse[]) => void): void {
        new HttpConnectionBuilder<CampeonatoResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    
    getCampeonatosInscrito(idUsuario: number,handlerSucess: (value: CampeonatoResponse[]) => void): void {
        new HttpConnectionBuilder<CampeonatoResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint(`campeonatos/inscricao/${idUsuario}`)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    
    getCampeonatosAbertos(handlerSucess: (value: CampeonatoResponse[]) => void): void {
        new HttpConnectionBuilder<CampeonatoResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos/abertos')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    
    getCampeonatosFechados(handlerSucess: (value: CampeonatoResponse[]) => void): void {
        new HttpConnectionBuilder<CampeonatoResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos/fechados')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    
    getCampeonatosIniciados(handlerSucess: (value: CampeonatoResponse[]) => void): void {
        new HttpConnectionBuilder<CampeonatoResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos/iniciados')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    

    deletar(campeonatoId: number, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos/'+ campeonatoId)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildDelete();
    }
}