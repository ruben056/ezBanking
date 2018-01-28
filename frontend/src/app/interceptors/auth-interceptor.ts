import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';

import { LocalJwtStorageService } from '../services/local-jwt-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  
  constructor(private localJwtStorageService: LocalJwtStorageService){}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    const idToken = this.localJwtStorageService.getJsonWebToken();
    
    if(idToken) {
      console.log('AuthInterceptor : adding header : Authorization : ' + idToken + ' to request before sending');
      const authorizedRequest = req.clone({
        headers: req.headers.set('Authorization', idToken)
      });
      
      return next.handle(authorizedRequest);
    }else {
      console.log('AuthInterceptor : sending request as is');
      return next.handle(req);
    }
    
  }
  
}
