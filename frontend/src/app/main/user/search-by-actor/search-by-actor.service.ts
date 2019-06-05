import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchByActorService {

  apiURL: string = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  findMoviesByActorId(actorId: number){
    return this.http.get<any>(this.apiURL + '/movies/actor/' + actorId);
  }
}
