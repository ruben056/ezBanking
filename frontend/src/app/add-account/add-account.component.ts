import { Component, HostBinding, Input } from '@angular/core';
import { Http, Response } from '@angular/http';
//import 'rxjs/add/operator/map';

@Component({
  selector: '[app-add-account]',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent {

  @Input()header: string;
  ACCOUNT_API_PREFIX_NEW:string = "http://localhost:4200/api/account/new/";
//  @HostBinding('attr.class') cssClass = 'ui message';
  
  //TODO use location to have current url context... iso hardcoding localhost...
  constructor(private http: Http) {
  
   }
  
  
  createAccount(accountHolder:string):void{
    // TODO call rest api: 
    // {accountHolder} Method post
    console.log('creating account for Accountholder : ' + accountHolder);
    this.http.post(this.ACCOUNT_API_PREFIX_NEW+accountHolder, {})
      .subscribe(
        (val) => {
            console.log("POST call successful value returned in body", 
                        JSON.stringify(val.json()));
        },
        response => {
            console.log("POST call in error", response);
        },
        () => {
            console.log("The POST observable is now completed.");
    });
  }


}
