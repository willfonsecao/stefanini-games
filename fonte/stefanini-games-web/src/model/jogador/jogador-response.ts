import { TimeResponse } from './../time/time-response';
import { UsuarioResponse } from './../user/usuario';

export class JogadorResponse{

    id: number
	usuario: UsuarioResponse;
    time: TimeResponse;
    
}