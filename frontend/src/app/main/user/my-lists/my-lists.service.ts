import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { List } from 'src/app/models/list.model';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class MyListsService {

  apiURL: string = 'http://localhost:8084';

  constructor(private http: HttpClient, private cookieService: CookieService) { }

  getLists(){
    return this.http.get<List[]>( this.apiURL + '/userlists/' + this.cookieService.get('userId'));
  }

  addList(name: string){  
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8'); 
    var o = { listName: name, user: {userId: this.cookieService.get('userId'), username: this.cookieService.get('username')}} 
    return this.http.post<any>(this.apiURL + '/addList', JSON.stringify(o), {headers:headers});
  }
}
