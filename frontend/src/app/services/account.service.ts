import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

@Injectable()
export class AccountService {

  ACCOUNT_API_NEW   = 'http://localhost:4200/api/account/new/';
  ACCOUNT_API_LIST  = 'http://localhost:4200/api/account/';
  
  ACCOUNT_API_DEPOSIT   = 'http://localhost:4200/api/account/transaction/deposit';
  ACCOUNT_API_TRANSFER  = 'http://localhost:4200/api/account/transaction/transfer';
  
  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.ACCOUNT_API_LIST);
  }
  
  createAccount(accountHolder: string): Observable<any> {
    return this.http.post(this.ACCOUNT_API_NEW + accountHolder, {});
  }
  
  makeDeposit(accountId: string, amount: number): Observable<any> {
    const depositCmd = {
      'amount' : amount,
      'accountId' : accountId
    };
    
    const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.post(this.ACCOUNT_API_DEPOSIT, depositCmd, headers);
  }
  
  
  doTransfer(amount: number, creditAccountId: string, debetAccountId: string): Observable<any> {
      
    const transferCmd = {
      'amount' : amount,
      'debetAccountId' : debetAccountId,
      'creditAccountId' : creditAccountId
    };
    
    const headers = new Headers({ 'Content-Type': 'application/json' });
    return this.http.post(this.ACCOUNT_API_TRANSFER, transferCmd, headers);
  }

}
