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

    buscar(handlerSucess: (value: CampeonatoResponse[]) => void): void {
        new HttpConnectionBuilder<CampeonatoResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }

    deletar(pessoaID: number, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('campeonatos/'+pessoaID)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildDelete();
    }
}