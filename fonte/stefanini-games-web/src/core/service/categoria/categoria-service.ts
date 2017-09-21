import { CategoriaResponse } from './../../../model/categoria/categoria-response';
import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { ToastController, LoadingController } from "ionic-angular";
import { HttpConnectionBuilder } from "../../infra/http/http-connection-builder";

@Injectable()
export class CategoriaService {

    constructor(private http: Http,
        private toastCtrl: ToastController,
        private loadingCtrl: LoadingController) {

    }

    cadastrar(categoria: CategoriaResponse, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('categorias')
            .addParameter(categoria)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .generateParametersJson()
            .showToastSucess()
            .buildPost();
    }

    buscar(handlerSucess: (value: CategoriaResponse[]) => void): void {
        new HttpConnectionBuilder<CategoriaResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('categorias')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }

    deletar(pessoaID: number, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('categorias/'+pessoaID)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildDelete();
    }
}