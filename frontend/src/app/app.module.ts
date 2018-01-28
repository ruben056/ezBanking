import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';

import {NgbModule, NgbModalModule, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { AddAccountComponent } from './add-account/add-account.component';
import { AccountListComponent } from './account-list/account-list.component';

import { AccountService } from './services/account.service';
import { DepositComponent } from './deposit/deposit.component';
import { TransferComponent } from './transfer/transfer.component';
import { AuthService } from './services/auth.service';
import { LocalJwtStorageService } from './services/local-jwt-storage.service';

import { AuthInterceptor } from './interceptors/auth-interceptor';

@NgModule({
  declarations: [
    AppComponent,
    AddAccountComponent,
    AccountListComponent,
    DepositComponent,
    TransferComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    NgbModalModule.forRoot()
  ],
  providers: [
    LocalJwtStorageService,
    AccountService,
    AuthService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
