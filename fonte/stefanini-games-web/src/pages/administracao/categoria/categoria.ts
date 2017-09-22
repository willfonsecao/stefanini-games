import { CategoriaResponse } from './../../../model/categoria/categoria-response';
import { CategoriaService } from './../../../core/service/categoria/categoria-service';
import { Component, OnInit } from "@angular/core";
import { NavController } from "ionic-angular";

@Component({
    selector: 'page-categoria',
    templateUrl: 'categoria.html'
  })
  export class CategoriaPage implements OnInit{

    categorias: CategoriaResponse[];
    categoria: CategoriaResponse = new CategoriaResponse();
    
    constructor(public navCtrl: NavController,
      private categoriaService: CategoriaService) {
        
      }

      isNomeInvalido(): boolean{
        return this.categoria.nome == null || this.categoria.nome == "";
      }
      
      ngOnInit(): void {
        this.buscar();
      }

      buscar(){
        this.categoriaService.buscar(c => { this.categorias = c});
      }

      editar(item: CategoriaResponse){
        this.categoria = item;
        this.categorias.splice(this.categorias.indexOf(this.categoria), 1);
      }

      salvar(){
        this.categoriaService.cadastrar(this.categoria,
          (novaCategoria) => {
            this.categorias.push(novaCategoria);
            this.categoria.nome = null;
          });
      }

      deletar(item: CategoriaResponse){
        this.categoriaService.deletar(item.id, 
          () => {
            this.categorias.splice(this.categorias.indexOf(item), 1);
          });
      }

  }