import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { Password } from '../models/password.model';
import { RegisterService } from './register.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  password: Password = new Password();
  isSubmitTriggered: boolean = false;
  constructor(private _registerService: RegisterService,
              private toastr: ToastrService) { }

  ngOnInit() {
  }

  register(form: any){
    this.isSubmitTriggered = true;
    if(form.form.valid && this.isEmailRegexValid()){
      this.user.password = this.password.first;
      this._registerService.register(this.user).subscribe(res => {
        console.log(res);
        this.user = new User();
        this.password = new Password();
        this.isSubmitTriggered = false;
        this.toastr.success('User successfully added.', 'Welcome!');
      }, error => {
        this.toastr.error('Something went wrong while trying to add new user.', 'Error!');
      }
      
      )
    }
  }

  isEmailRegexValid(){
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(re.test(this.user.email)){
      return true;
    }
    else{
      return false;
    }
  }

}
