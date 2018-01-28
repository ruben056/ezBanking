import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { AuthService } from './services/auth.service';
import { AccountListComponent } from './account-list/account-list.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  loggedIn = false;
  @ViewChild(AccountListComponent) accountList: AccountListComponent;
  
  constructor(private authService: AuthService) {}  
  
  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      this.accountList.refreshAccounts();
      this.loggedIn = true;
    }else{
      this.loggedIn = false;
    }
  }

  doLogin(loginForm: FormGroup) {
    console.log('submitting : ', loginForm.value);
    const userAndPwd = loginForm.value;

    //user1,pwd
    this.authService.login(userAndPwd['user'], userAndPwd['password'])
      .subscribe(result => {
        console.log('login ok');
        this.accountList.refreshAccounts();
        this.loggedIn = true;
      },
      err => console.log('error : ', err),
      () => console.log('login finalized'));
  }
  
  doLogout() {
    this.authService.logout();
    this.accountList.clearAccounts();
    this.loggedIn = false;
  }
}
