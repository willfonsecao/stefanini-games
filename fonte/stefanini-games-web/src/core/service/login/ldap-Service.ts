import { ADLoginDTO } from './../../../model/user/ADLoginDTO';
import { ADUserDTO } from './../../../model/user/ADUserDTO';
import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import { ToastController, LoadingController } from "ionic-angular";
import { HttpConnectionBuilderLogin } from '../../infra/http/http-connection-builder-login';

@Injectable()
export class LdapService {

    constructor(private http: Http,
        private toastCtrl: ToastController,
        private loadingCtrl: LoadingController) {

    }

    buscarPorUsername(username: string,handlerSucess: (value: ADUserDTO[]) => void): void {
        new HttpConnectionBuilderLogin<ADUserDTO[]>(this.http, this.loadingCtrl)
            .addToastrUtil(this.toastCtrl)
            .addParameter(username)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }

    buscarPorLogin(adLoginDTO: ADLoginDTO, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilderLogin<any>(this.http, this.loadingCtrl)
            .addEndPoint('ad-login')
            .addParameter(adLoginDTO)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .generateParametersJson()
            .buildPost();
    }
}