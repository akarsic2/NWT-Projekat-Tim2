import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  apiURL: string = 'http://localhost:8082';

  constructor(private http: HttpClient) { }

  register(user: User){
    user.isAdmin = false;
    return this.http.post<any>(this.apiURL + '/add', user);
  }
}
