import { EndPointUtil } from './../../../util/endpoint-util';
import { UsuarioResponse } from './../../../model/user/usuario';
import { Component } from "@angular/core";
import { Camera, CameraOptions } from '@ionic-native/camera';
import { ActionSheetController, NavParams, NavController } from "ionic-angular";
import { FileTransfer, FileUploadOptions, FileTransferObject } from '@ionic-native/file-transfer';

@Component({
    selector: 'page-editar-perfil',
    templateUrl: 'editar-perfil.html'
  })
export class EditarPerfilPage {

    options: CameraOptions;
    optionsBiblioteca: CameraOptions;
    fileTransfer: FileTransferObject;
    usuario: UsuarioResponse;
    imageUsuario: any = '';
    urlImageUsuario: any = 'assets/user.jpg';

    constructor(private camera: Camera,
                private actionSheetCtrl: ActionSheetController,
                private params: NavParams,
                public navCtrl: NavController,
                private transfer: FileTransfer){
       
        this.popularUsuario();
        this.configurarOptionsBiblioteca();
        this.configurarOptionsCamera();
        this.fileTransfer = this.transfer.create();
    }

    alterarFoto() {
        let actionSheet = this.actionSheetCtrl.create({
          title: 'Alterar Foto',
          buttons: [
              {
                  text: 'Câmera',
                  icon: 'ios-camera-outline',
                  handler: () => {
                      this.camera.getPicture(this.options).then((imageData) => {
                        this.imageUsuario = imageData;
                        this.upload();

                      }, (err) => {
                          // Handle error
                      });
                  }
              }, {
                  text: 'Biblioteca',
                  icon: 'ios-images-outline',
                  handler: () => {
                      this.camera.getPicture(this.optionsBiblioteca).then((imageData) => {
                        this.imageUsuario = imageData;
                        this.upload();

                      }, (err) => {
                          // Handle error
                      });
                  }
              }, {
              text: 'Cancelar',
              role: 'cancel',
              handler: () => {

              }
            }
          ]
        });
        actionSheet.present();
      }

    upload() {
        let options: FileUploadOptions = {
            fileKey: 'file',
            fileName: this.usuario.nomeRede+'.jpg',
            headers: {}
        }
      
        this.fileTransfer.upload(this.imageUsuario, EndPointUtil.endPointGames+'usuarios/photo', options)
            .then((data) => {
                this.urlImageUsuario = this.imageUsuario;
            }, (err) => {
                // error
            })
    }

    atualizarImagemUsuario() {
        if(!this.usuario.foto) {
            this.urlImageUsuario = 'assets/user.jpg';
        }
        this.urlImageUsuario = EndPointUtil.endPointImages + this.usuario.nomeRede + '.jpg';
    }
    
    configurarOptionsCamera() {
        this.options = {
            quality: 50,
            destinationType: this.camera.DestinationType.FILE_URI,
            encodingType: this.camera.EncodingType.JPEG,
            mediaType: this.camera.MediaType.PICTURE,
            saveToPhotoAlbum: true
        }
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

    popularUsuario(){
        this.usuario = this.params.get('usuario');
        if(this.usuario){
            this.atualizarImagemUsuario();
        }else{
            this.navCtrl.pop();
        }
    }

}