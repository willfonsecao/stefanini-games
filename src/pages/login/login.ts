import { Key } from './../../core/infra/local-storage/key';
import { LocalStorageService } from './../../core/infra/local-storage/local-storage-service';
import { UsuarioResponse } from './../../model/user/usuario';
import { ADUserDTO } from './../../model/user/ADUserDTO';
import { UsuarioService } from './../../core/service/user/user-service';
import { TabsPage } from './../tabs/tabs';
import { NavController } from 'ionic-angular';
import { ADLoginDTO } from './../../model/user/ADLoginDTO';
import { Component, OnInit } from "@angular/core";
import { LdapService } from '../../core/service/login/ldap-Service';

@Component({
    selector: 'login',
    templateUrl: 'login.html'
})
export class LoginPage implements OnInit{
    
    userLogin: ADLoginDTO = new ADLoginDTO();
    isInvalido: boolean = false;
    
    constructor(private ldapService: LdapService,
        private navCtrl: NavController,
        private userService: UsuarioService,
        private localStorage: LocalStorageService) {
            
        }
        
        ngOnInit(): void {
            if(this.localStorage.pull(Key.usuarioLogado)){
                this.navCtrl.push(TabsPage);
            }
        }

    logar(){
        this.ldapService.buscarPorLogin(this.userLogin,(user: ADUserDTO) => {
            if(user){

                this.userService.findByUserName(this.userLogin.username, (usuario) => {
                    
                    if (usuario) {
                        this.localStorage.push(Key.usuarioLogado, usuario);
                        this.navCtrl.push(TabsPage);
                    } else {
                        user.username = this.userLogin.username;
                        this.userService.cadastrar(new UsuarioResponse(user, false), (usuarioCadastrado) => {
                            this.localStorage.push(Key.usuarioLogado, usuarioCadastrado);
                            this.navCtrl.push(TabsPage);
                        })
                    }
                })

            }else{
                this.isInvalido = true;
                this.userLogin.username = null;
                this.userLogin.password = null;
            }
        });
    }

    hideInvalidMessage(){
        this.isInvalido = false;
    }
}