export class Account {
  accountNr: String;
  holder: string;
  balance: number;
  
  constructor(accountJson:any){
    this.accountNr = accountJson.id;
    this.holder = accountJson.accountHolder;
    this.balance= accountJson.balance;
  }
  
}
