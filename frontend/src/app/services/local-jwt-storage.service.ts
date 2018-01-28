import { Injectable } from '@angular/core';

export const JWT_NAME = 'ezbank-jwt-token';

@Injectable()
export class LocalJwtStorageService {

  constructor() { }
  
  storeJsonWebToken(jwt: string): void {
        // TODO expiration stuff...
    localStorage.setItem(JWT_NAME, jwt);
  }
  
  getJsonWebToken(): string {
    return localStorage.getItem(JWT_NAME);
  }
  
  removeJsonWebToken(): void {
   localStorage.removeItem(JWT_NAME);
  }

}
