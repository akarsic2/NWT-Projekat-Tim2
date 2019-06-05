import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Genre } from 'src/app/models/genre.model';

@Injectable({
  providedIn: 'root'
})
export class AddGenreService {

  apiURL: string = 'http://localhost:8086';

  constructor(private http: HttpClient) { }

  getGenres(){
    return this.http.get<Genre[]>( this.apiURL + '/zanrovi');
  }

  addGenre(name: string){
    var obj = {id: 0, zanr: name};
    
    return this.http.post<any>(this.apiURL + '/add', name);
  }

  findMoviesByGenreId(genreId: number){
    return this.http.get<any>(this.apiURL + '/movies/genre/' + genreId);
  }
}
