import { OnInit, Component } from "@angular/core";
import { CampeonatoResponse } from "../../../model/campeonato/campeonato-response";
import { NavParams } from "ionic-angular";
import { DataUtil } from "../../../util/data-util";

@Component({
    selector: 'page-visualizar-campeonato',
    templateUrl: 'visualizar-campeonato.html'
  })
  export class VisualizarCampeonatoPage implements OnInit {
   
    campeonato: CampeonatoResponse;

    constructor(private params: NavParams,){
    }
   
    ngOnInit(): void {
        this.campeonato = this.params.get('campeonato');
    }

    hasPremio(){
        return this.isPrimeiroLugar() || this.isSegundoLugar() || this.isTerceiroLugar();
    }

    isPrimeiroLugar(): boolean{
        return this.campeonato.premioCampeao != null && this.campeonato.premioCampeao != "";
    }
    
    isSegundoLugar(){
        return this.campeonato.premioSegundoColocado != null && this.campeonato.premioSegundoColocado != "";
    }
   
    isTerceiroLugar(){
        return this.campeonato.premioTerceiroColocado != null && this.campeonato.premioTerceiroColocado != "";
    }

    getDataFormatada(data: Date) {
        return DataUtil.getDataFormatada(data);
    }
}