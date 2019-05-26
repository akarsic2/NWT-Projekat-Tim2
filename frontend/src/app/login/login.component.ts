import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  constructor(private _loginService: LoginService, private _router:Router) { }

  ngOnInit() {
  }

  
  redirectToRegister(){
    this._router.navigate(['register']);
  }

  login(){
    this._loginService.login(this.username, this.password);  //subscribe
    // if admin
    this._router.navigate(['main/add-movie']);
    
  }

}
