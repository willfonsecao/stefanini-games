import { UsuarioService } from './../../../core/service/user/user-service';
import { UsuarioResponse } from './../../../model/user/usuario';
import { Component, OnInit } from "@angular/core";


@Component({
    selector: 'page-administradores',
    templateUrl: 'administradores.html'
  })
  export class AdministradoresPage implements OnInit{
      
    username: string;
    administradores: UsuarioResponse[];
    adm: UsuarioResponse;

    constructor(private usuariosService: UsuarioService) {

    }

    ngOnInit(): void {
        this.getAdms();
    }

    isNomeInvalido(): boolean {
        return this.username == null || this.username == "";
    }

    salvar() {
        this.usuariosService.salvarAdm(this.username, (adm) => {
            if (adm) {
                this.adm = adm;
                this.administradores.push(adm);
            }
        })
    }

    excluir(user: UsuarioResponse) {
        this.usuariosService.removerAdm(user.nomeRede, (value) => {
            this.administradores.splice(this.administradores.indexOf(user), 1);
        });
    }

    getAdms() {
        this.usuariosService.buscarAdms((adms) => {
            if (adms) {
                this.administradores = adms;
            }
        })
    }
}