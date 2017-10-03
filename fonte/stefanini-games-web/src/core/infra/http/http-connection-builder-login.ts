import { EndPointUtil } from './../../../util/endpoint-util';
import { Http, Response } from "@angular/http";

import 'rxjs/add/operator/map';
import { Subscription } from "rxjs/Subscription";
import { ToastController, LoadingController, Loading } from "ionic-angular";

export class HttpConnectionBuilderLogin<T> {
    url: string = '';
    endPoint: string = '';

    parameters: any = [];
    parametersPagination: any = [];
    file: any = null;
    showMsgError: boolean = true;
    showMsgSucess: boolean = false;
    loader: Loading;

    handlerSucess: (value: T) => void;
    handlerError: (value: any) => void;

    toastController: ToastController

    constructor(protected http: Http, protected loadingCtrl: LoadingController) {
        this.loader = this.loadingCtrl.create({
            content: "Aguarde...",
        });
    }

    addServerDomain(urlServerDomain: string): HttpConnectionBuilderLogin<T> {
        this.url += urlServerDomain;

        return this;
    }

    addEndPoint(endPoint: string): HttpConnectionBuilderLogin<T> {
        this.endPoint += endPoint;

        return this;
    }

    addPage(page: number): HttpConnectionBuilderLogin<T> {
        this.addPagination(10, page);

        return this;
    }

    addPagination(size: number, page: number): HttpConnectionBuilderLogin<T> {
        this.parametersPagination = { 'size': size, 'page': page };

        return this;
    }

    addParameter(parameters: any): HttpConnectionBuilderLogin<T> {
        this.parameters = parameters

        return this;
    }

    addFileParameter(file: any): HttpConnectionBuilderLogin<T> {
        this.file = file

        return this;
    }

    addHandlerError(handlerError: (value: any) => void): HttpConnectionBuilderLogin<T> {
        this.handlerError = handlerError

        return this;
    }

    addHandlerSucess(handlerSucess: (value: T) => void): HttpConnectionBuilderLogin<T> {
        this.handlerSucess = handlerSucess

        return this;
    }

    addToastrUtil(toastController: ToastController): HttpConnectionBuilderLogin<T> {
        this.toastController = toastController;

        return this;
    }

    generateFormData(keyForm: string, keyFile?: string): HttpConnectionBuilderLogin<T> {
        let formData: FormData = new FormData();
        formData.append(keyForm, JSON.stringify(this.parameters));

        if (keyFile != null) {
            formData.append(keyFile, this.file, this.file.name);
        }

        this.parameters = formData;

        return this;
    }

    generateParametersJson(): HttpConnectionBuilderLogin<T> {
        this.parameters = JSON.stringify(this.parameters);

        return this;
    }

    hideToastErro(): HttpConnectionBuilderLogin<T> {
        this.showMsgError = false;

        return this;
    }

    showToastSucess(): HttpConnectionBuilderLogin<T> {
        this.showMsgSucess = true;

        return this;
    }

    buildPost(): Subscription {
        this.showLoading();
        return this.http.post(this.createURL(), this.parameters)
            .map(response => this.doParseJson(response))
            .subscribe(
            res => this.doSucess(res),
            err => this.doError(err),
            () => this.loader.dismiss()
            );
    }

    buildGet(): Subscription {
        this.showLoading();
        return this.http.get(this.createURL() + this.createQueryString())
            .map(response => this.doParseJson(response))
            .subscribe(
            (res: T) => this.doSucess(res),
            err => this.doError(err),
            () => this.loader.dismiss()
            );
    }

    buildDelete(): Subscription {
        this.showLoading();
        return this.http.delete(this.createURL() + this.createQueryString())
            .map(response => this.doParseJson(response))
            .subscribe(
            (res: T) => this.doSucess(res),
            err => this.doError(err),
            () => this.loader.dismiss()
            );
    }

    private doParseJson(response: Response): T {
        try {
            return <T>(response.json() || {});
        } catch (e) {
            return null;
        }
    }

    private doSucess(response: T) {
        if (this.handlerSucess) {
            this.handlerSucess(<T>response);
        }

        if (this.showMsgSucess) {
            let toast = this.toastController.create({
                message: 'Operação realizada com sucesso!',
                duration: 3000
            });
            toast.present();
        }

    }

    private doError(err: any) {
        if (this.handlerError) {
            this.handlerError(err);
        }

        if (this.showMsgError) {
            if (this.toastController) {
                let body = err.json() || err;
                let message = 'Ocorreu um erro. Tente novamente mais tarde ou entre em contato com a equipe técnica da Stefanini.';
                console.log(body.erros);
                if (body.erros) {
                    message = '';
                    for (var i = 0; i < body.erros.length; i++) {
                        message += body.erros[i] + '\n';
                    }
                }
                let toast = this.toastController.create({
                    message: message,
                    position: 'middle',
                    duration: 3000
                });
                toast.present();
            }
        }
        
        this.loader.dismiss();

        throw err;
    }

    private createQueryString(): string {
        let params = new URLSearchParams();

        for (let key in this.parameters) {
            if (this.parameters.hasOwnProperty(key)) {
                if (this.parameters[key]) {
                    params.set(key, this.parameters[key])
                }
            }
        }

        for (let key in this.parametersPagination) {
            if (this.parametersPagination.hasOwnProperty(key)) {
                params.set(key, this.parametersPagination[key])
            }
        }

        let queryString = params.toString();

        if (queryString) {
            return '?' + queryString;
        }

        return '';
    }

    private createURL(): string {
        if (this.url == '') {
            this.addApplicationServerDomain();
        }

        return this.url + this.endPoint;
    }

    private addApplicationServerDomain() {
        this.url += EndPointUtil.endPointLDAP;
    }

    private showLoading() {
        this.loader.present();
    }
    
}