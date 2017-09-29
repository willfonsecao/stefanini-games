import { ADUserDTO } from './ADUserDTO';
export class UsuarioResponse {
    
    id: number;
    foto: boolean;
    nome: string;
    sobrenome: string;
    nomeRede: string;
    administrador: boolean;

    constructor(adUser: ADUserDTO, isAdm: boolean){
        this.nome = adUser.firstName;
        this.sobrenome = adUser.lastName;
        this.nomeRede = adUser.username;
        this.administrador = isAdm;
        this.foto = false;
    }

}