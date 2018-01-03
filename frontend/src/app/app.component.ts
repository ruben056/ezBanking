import { Component } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  accounts:any[];
  ACCOUNT_API_LIST:string = "http://localhost:4200/api/account/";
  
  constructor(private http:Http){
    this.accounts=[
      {id: 'id1', accountHolder: 'testAccountHoder', balance: '20.0'},
      {id: 'id2', accountHolder: 'testAccountHoder2', balance: '40.0'}
    ];
    
    this.refreshAccounts();
  }
  
  refreshAccounts():void{
    this.http.get(this.ACCOUNT_API_LIST).map(
        (res) => {
            return res.json();
        }).subscribe(
          (val) => {
              console.log('GET call successful value returned in body',val);
              this.accounts = val;
          },
          response => {
              console.log('GET call in error', response);
          },
          () => {
              console.log('GET POST observable is now completed.');
      });
  }
  
}
