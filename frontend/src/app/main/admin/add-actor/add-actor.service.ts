import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Actor } from '../../../models/actor.model';

@Injectable({
  providedIn: 'root'
})
export class AddActorService {

  apiURL: string = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  getActors(){
    return this.http.get<Actor[]>( this.apiURL + '/glumci');
  }

  addActor(actor: Actor){
    
    return this.http.post<any>(this.apiURL + '/add', actor);
  }
}
