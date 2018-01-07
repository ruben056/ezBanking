import { Component, OnInit, Input } from '@angular/core';

import { AccountService } from '../services/account.service'

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  @Input()account: any;
  
  constructor(private accountService:AccountService) { }

  ngOnInit() {
    console.log("depositComponent for account : " + this.account.id);
  }

  makeDeposit(amount: number){
    console.log("deposit to: " + this.account.id);
    this.accountService.makeDeposit(this.account.id, amount).subscribe(
      (val) => console.log("deposited : ", amount ),
      (err) => console.log("failed to deposit : ", err),
      () => console.log("deposit finished ")
    );
  }
}
