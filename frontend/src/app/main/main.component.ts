import { Component, OnInit, AfterViewInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { User } from '../models/user.model';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  isAdmin : boolean = true;

  constructor(private cookieService: CookieService,
              private _router: Router) { }

  ngOnInit() {
    this.isAdmin = JSON.parse(this.cookieService.get('isAdmin'));
    // this.isAdmin = this._userService.user.isAdmin;
    
  }

  logOut(){
    this.cookieService.deleteAll();
    this._router.navigate(['']);
  }

}
