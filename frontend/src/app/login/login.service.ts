import { Injectable, Output, EventEmitter } from '@angular/core';
import { UserBasic } from '../models/UserBasic.model';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  apiURL: string = 'http://localhost:8082';

  constructor(private http: HttpClient) { }

  login(user: UserBasic){
    return this.http.post<User>( this.apiURL + '/user', user);
  }
}
