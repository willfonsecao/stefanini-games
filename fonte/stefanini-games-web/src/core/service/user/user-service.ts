import { UsuarioResponse } from './../../../model/user/usuario';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { LoadingController, ToastController } from 'ionic-angular';
import { HttpConnectionBuilder } from '../../infra/http/http-connection-builder';

@Injectable()
export class UsuarioService {

    constructor(private http: Http,
        private toastCtrl: ToastController,
        private loadingCtrl: LoadingController) {

    }

    cadastrar(usuario: UsuarioResponse, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios')
            .addParameter(usuario)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .generateParametersJson()
            .showToastSucess()
            .buildPost();
    }
    
    salvarAdm(username: string, handlerSucess: (value: UsuarioResponse) => void): void {
        new HttpConnectionBuilder<UsuarioResponse>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios/adm/'+username)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .generateParametersJson()
            .showToastSucess()
            .buildPost();
    }

    getPhoto(username: string,handlerSucess: (value: string) => void): void {
        new HttpConnectionBuilder<string>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios/photo')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }

    buscar(handlerSucess: (value: UsuarioResponse[]) => void): void {
        new HttpConnectionBuilder<UsuarioResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    
    buscarAdms(handlerSucess: (value: UsuarioResponse[]) => void): void {
        new HttpConnectionBuilder<UsuarioResponse[]>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios/administradores')
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    
    findById(pessoaID: number,handlerSucess: (value: UsuarioResponse) => void): void {
        new HttpConnectionBuilder<UsuarioResponse>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios/' + pessoaID)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }
    
    findByUserName(username: string,handlerSucess: (value: UsuarioResponse) => void): void {
        new HttpConnectionBuilder<UsuarioResponse>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios/username/' + username)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildGet();
    }

    deletar(pessoaID: number, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios/'+ pessoaID)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .buildDelete();
    }

    removerAdm(username: string, handlerSucess: (value: any) => void): void {
        new HttpConnectionBuilder<any>(this.http, this.loadingCtrl)
            .addEndPoint('usuarios/adm/'+username)
            .addToastrUtil(this.toastCtrl)
            .addHandlerSucess(handlerSucess)
            .generateParametersJson()
            .showToastSucess()
            .buildDelete();
    }
}