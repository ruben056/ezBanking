import { Component, OnInit } from '@angular/core';

import { AccountService } from '../services/account.service'

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {

  accounts:any[];
  
  constructor(private accountService:AccountService) {
  }

  ngOnInit() {
//    this.refreshAccounts(); // uncomment when finished with auth.
  }
  
  refreshAccounts(){
    this.accountService.getAll().subscribe(
      accounts => this.accounts = accounts,
      error => console.log('error : ', error),
      () => console.log('service call finished')
    );
  }
  
  clearAccounts(){
    this.accounts = [];
  }

}
