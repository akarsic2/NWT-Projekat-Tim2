import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { UserBasic } from '../models/UserBasic.model';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserBasic = new UserBasic();
  isValidUser: boolean = true;
  userExists: boolean = true;
  constructor(private _loginService: LoginService, 
              private _router:Router, 
              private cookieService: CookieService) { }

  ngOnInit() {
    if (this.cookieService.check('isAdmin')){
      var isAdmin = JSON.parse(this.cookieService.get('isAdmin'));
      if (isAdmin != null){
        isAdmin == true ? this._router.navigate(['main/add-movie']) : this._router.navigate(['main/my-lists']);
      }
    }
  }

  
  redirectToRegister(){
    this._router.navigate(['register']);
  }

  login(){
    
    this._loginService.login(this.user).subscribe(res => {
      if (res == null){
        this.userExists = false;
      }
      else {
        this.cookieService.set('username', res.username);
        this.cookieService.set('password', res.password);
        this.cookieService.set('isAdmin', String(res.isAdmin));
        this.cookieService.set('userId', res.id.toString());
        res.isAdmin == true ? this._router.navigate(['main/add-movie']) : this._router.navigate(['main/my-lists']);
      }
    });  
    
  }

}
