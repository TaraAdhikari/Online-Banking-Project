import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loggedIn: boolean;

  constructor(private loginService: LoginService, private router : Router) {
    if(localStorage.getItem('PortalAdminHasLoggedIn') == '') {
      this.loggedIn = false;
    } else {
      this.loggedIn = true;
    }
  }

  logout(){
    this.loginService.logout().subscribe(
      res => {
        localStorage.setItem('PortalAdminHasLoggedIn', '');
      },
      err => console.log(err)
    );
    location.reload();
    this.router.navigate(['/login']);
  }

  getDisplay() {//if the user has logged in, we will just return empty otherwise we will jsut return none.
    if(!this.loggedIn){
      return "none";
    } else {
      return "";
    }
  }

  ngOnInit() {
  }

}
