/*import { Component, OnInit } from '@angular/core';
//import {Observable}  from '@rxjs/Observable';  //react library basically for returning observable when we try to communicate with backend
import {LoginService} from '../login.service';  // for Login service component

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loggedIn: boolean;
  username: string;
  password: string;

  constructor(private loginService: LoginService) {
    if(localStorage.getItem('PortalAdminHasLoggedIn')=='' && localStorage.getItem('PortalAdminHasLoggedIn')==null){
      this.loggedIn=false;
    }else{
      this.loggedIn=true;
    }
  }

  onSubmit(){
    this.loginService.sendCredential(this.username, this.password).subscribe(   //will send the username and password to the backend
      res => {
        this.loggedIn=true;  //if the login get successful, then this.loggedIn=true
        localStorage.setItem('PortalAdminHasLoggedIn','true');
        location.reload();  //will reload the page
      },
      err=>console.log(err)   //if username and password is not correct then, we can simply log the error with this
    );
  }
  ngOnInit() {
  }

}*/

import { Component, OnInit } from '@angular/core';
import {Observable}  from 'rxjs/Observable';
import {LoginService} from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loggedIn: boolean;
  username: string;
  password: string;

  constructor (private loginService: LoginService) {
    if(localStorage.getItem('PortalAdminHasLoggedIn') == '' || localStorage.getItem('PortalAdminHasLoggedIn') == null) {
      this.loggedIn = false;
    } else {
      this.loggedIn = true;
    }
  }

  onSubmit() {
    this.loginService.sendCredential(this.username, this.password).subscribe(
      res => {
        this.loggedIn=true;
        localStorage.setItem('PortalAdminHasLoggedIn', 'true');
        location.reload();
      },
      err => console.log(err)
    );
  }

  ngOnInit() {}

}

