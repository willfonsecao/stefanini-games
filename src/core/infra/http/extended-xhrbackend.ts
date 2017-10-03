import { Injectable } from '@angular/core';
import { Request, XHRBackend, BrowserXhr, ResponseOptions, XSRFStrategy, Response } from '@angular/http';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class ExtendedXHRBackend extends XHRBackend {

  constructor(browserXhr: BrowserXhr, baseResponseOptions: ResponseOptions, xsrfStrategy: XSRFStrategy) {
    super(browserXhr, baseResponseOptions, xsrfStrategy);
  }

  createConnection(request: Request) {
    
    let xhrConnection = super.createConnection(request);
    
    request.headers.set('Cache-Control', 'no-cache, no-store, must-revalidate');
    request.headers.set('Pragma', 'no-cache');
            
    if(!(request.getBody() instanceof FormData)) {
        request.headers.set('Content-Type', 'application/json');      
    }
            
    xhrConnection.response = xhrConnection.response.catch((error: Response) => {
      
      throw error;
    });

    return xhrConnection;
  }

}