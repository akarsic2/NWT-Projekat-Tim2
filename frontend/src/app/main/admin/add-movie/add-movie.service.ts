import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from '../../../models/movie.model';
import { Genre } from 'src/app/models/genre.model';
import { Actor } from 'src/app/models/actor.model';

@Injectable({
  providedIn: 'root'
})
export class AddMovieService {

  apiURL = "http://localhost:8091";
  apiURLGenres: string = 'http://localhost:8086';
  apiURLActors: string = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  addMovie(movie: Movie){
    var headers={
      headers: new HttpHeaders({
          'Content-Type': 'application/json'
      })
  }
    return this.http.post<any>(this.apiURL + '/add', JSON.stringify(movie), headers);
  }

  getGenres(){
    return this.http.get<Genre[]>( this.apiURLGenres + '/zanrovi');
  }

  getActors(){
    return this.http.get<Actor[]>(this.apiURLActors + '/glumci');
  }


  connectGenresToMovie(movieId: number, movieName: string, genres: Array<any>){
    var genreListIds = genres.map(x => x.id);
    var o = {movieId: movieId, movieName: movieName, genres: genreListIds};
    return this.http.post<Genre[]>( this.apiURLGenres + '/addgenrestomovie', o);
  }

  connectActorsToMovie(movieId: number, movieName: string, actors: Array<any>){
    var actorListIds = actors.map(x => x.id);
    var o = {movieId: movieId, movieName: movieName, actors: actorListIds};
    return this.http.post<Movie[]>(this.apiURLActors + '/addactorstomovie', o);
  }
}
