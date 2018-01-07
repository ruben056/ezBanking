import { Component, OnInit, Input } from '@angular/core';

import { AccountService } from '../services/account.service'

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  @Input() creditAccount:any;
  
  @Input()srcAccount: any;
  
  constructor(private accountService: AccountService) { }

  ngOnInit() {
    console.log("transferComponent for account : " + this.creditAccount.id);
  }
  
  doTransfer(amount: number, debetAccountId:string){
    console.log("transfer in from: " + this.creditAccount.id + " to " + debetAccountId);
    this.accountService.doTransfer(amount, this.creditAccount.id, debetAccountId).subscribe(
      val => console.log("transfer ok"),
      err => console.log("transfer nok : ", err),
      () => console.log("transfer done")
    );
  }

}
