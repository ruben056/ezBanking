import { Component, HostBinding, Input } from '@angular/core';
import { Http, Response } from '@angular/http';

import { AccountService } from '../services/account.service';

@Component({
  selector: '[app-add-account]',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent {

  @Input()header: string;
//  @HostBinding('attr.class') cssClass = 'ui message';  
  //TODO use location to have current url context... iso hardcoding localhost...
  constructor(private accountService: AccountService) {
  
   }
  
  
  createAccount(accountHolder:string):void{
    console.log('creating account for Accountholder : ' + accountHolder);
    this.accountService.createAccount(accountHolder)
      .subscribe(
        (val) => console.log("POST call successful value returned in body", JSON.stringify(val)),
        response => console.log("POST call in error", response),
        () => console.log("The POST observable is now completed.")
    );
  }


}
