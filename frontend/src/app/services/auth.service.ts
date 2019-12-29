import {Injectable} from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

import { LocalJwtStorageService } from './local-jwt-storage.service';


@Injectable()
export class AuthService {

  //proxy.conf.json no longer makes sense if we use correct url here (should go in environment.ts anyway...)
  private LOGIN_URL = 'http://localhost:8080/login';
  public jwt: string;

  constructor(private http: HttpClient, private localJwtStorageService: LocalJwtStorageService) {
        // TODO set token if saved in local storage
        /* not for now need to test backend...
         * */
  }

  isLoggedIn(): boolean {
    return this.jwt !== undefined;
  }

  isLoggedOout(): boolean{
    return !this.isLoggedIn();
  }

  // TODO use this to set expiration date when storing in localstorage (also modify localstorage service)
  getTokenExpirationDate(token: string): Date {
    const decoded = jwt_decode(token);

    if (decoded.exp === undefined) {
     return null;
    }

    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  login(username: string, password: string): Observable<boolean> {
    return this.http.post(
            `${this.LOGIN_URL}`,
            JSON.stringify({username: username, password: password}),
            { // TODO consider returning the jwt token in the body, then all these "hacks/workarounds" can go away
             observe: 'response' ,  // required to have full response in reponse (incl header, iso just body)
             responseType: 'text'  // required to avoid empty body to result in error for angular httpclient
            })
          .map((response: HttpResponse<any>) => {
            console.log('raw reponse : ' + JSON.stringify(response));
            const token = response.headers.get('Authorization');
            if (token) {
              console.log('reponse login : ' + token);
              this.jwt = token;
              this.localJwtStorageService.storeJsonWebToken(this.jwt);
              return true; // todo...
            }else {
              console.log('failed reponse login');
              return false;
            }
      });
  }

  logout() {
    this.jwt = null;
    this.localJwtStorageService.removeJsonWebToken();
  }
}
