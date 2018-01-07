import { Injectable } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';

import { Observable } from 'rxjs'
import 'rxjs/add/operator/map';

@Injectable()
export class AccountService {

  ACCOUNT_API_NEW:string = 'http://localhost:4200/api/account/new/';
  ACCOUNT_API_LIST:string = 'http://localhost:4200/api/account/';
  
  ACCOUNT_API_DEPOSIT:string = 'http://localhost:4200/api/account/transaction/deposit';
  ACCOUNT_API_TRANSFER:string = 'http://localhost:4200/api/account/transaction/transfer';
  
  constructor(private http:Http) { }

  getAll(): Observable<any>{
    return this.http.get(this.ACCOUNT_API_LIST).map(
      res => res.json());
  }
  
  createAccount(accountHolder: string): Observable<any>{
    return this.http.post(this.ACCOUNT_API_NEW+accountHolder, {})
      .map(res => res.json());
  }
  
  makeDeposit(accountId: string, amount: number):Observable<any>{
    let depositCmd = {
      'amount' : amount,
      'accountId' : accountId
    };
    
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.ACCOUNT_API_DEPOSIT, depositCmd, options)
      .map(res => res.json());
  }
  
  
  doTransfer(amount: number, creditAccountId : string, debetAccountId : string): Observable<any>{
      
    let transferCmd = {
      'amount' : amount,
      'debetAccountId' : debetAccountId,
      'creditAccountId' : creditAccountId
    };
    
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this.ACCOUNT_API_TRANSFER, transferCmd, options)
      .map(res => res.json());
  }

}
