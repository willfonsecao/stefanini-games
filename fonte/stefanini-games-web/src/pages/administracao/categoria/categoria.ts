import { CategoriaResponse } from './../../../model/categoria/categoria-response';
import { CategoriaService } from './../../../core/service/categoria/categoria-service';
import { Component, OnInit } from "@angular/core";
import { NavController } from "ionic-angular";
import { Camera, CameraOptions } from '@ionic-native/camera';
import { FileTransfer, FileUploadOptions, FileTransferObject } from '@ionic-native/file-transfer';
import { EndPointUtil } from '../../../util/endpoint-util';

@Component({
    selector: 'page-categoria',
    templateUrl: 'categoria.html'
  })
  export class CategoriaPage implements OnInit{

    categorias: CategoriaResponse[];
    categoria: CategoriaResponse = new CategoriaResponse();
    optionsBiblioteca: CameraOptions;
    fileTransfer: FileTransferObject;
    logoCategoria: any = '';
    
    constructor(public navCtrl: NavController,
                private categoriaService: CategoriaService,
                private camera: Camera,
                private transfer: FileTransfer) {

            this.configurarOptionsBiblioteca();
            this.fileTransfer = this.transfer.create();
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

      saveLogo(idCategoria: string) {
        this.camera.getPicture(this.optionsBiblioteca).then((imageData) => {
          this.logoCategoria = imageData;
          this.upload(idCategoria.toString());

        }, (err) => {
          // Handle error
        });

      }

      upload(idCategoria: string) {
        let options: FileUploadOptions = {
          fileKey: 'logo',
          fileName: idCategoria,
          headers: {}
        }

        this.fileTransfer.upload(this.logoCategoria, EndPointUtil.endPointGames+'categorias/logo', options)
          .then((data) => {
            this.logoCategoria = '';
          }, (err) => {
            // error
          })
      }

    configurarOptionsBiblioteca(){
      this.optionsBiblioteca = {
          quality: 50,
          destinationType: this.camera.DestinationType.FILE_URI,
          encodingType: this.camera.EncodingType.JPEG,
          mediaType: this.camera.MediaType.PICTURE,
          sourceType: this.camera.PictureSourceType.PHOTOLIBRARY
      }
  }

  }