import { Component, OnInit } from '@angular/core';
import { Actor } from 'src/app/models/actor.model';
import { ToastrService } from 'ngx-toastr';
import { AddMovieService } from '../../admin/add-movie/add-movie.service';
import { SearchByActorService } from './search-by-actor.service';

@Component({
  selector: 'app-search-by-actor',
  templateUrl: './search-by-actor.component.html',
  styleUrls: ['./search-by-actor.component.css']
})
export class SearchByActorComponent implements OnInit {

  actors: Array<Actor> = new Array();
  selectedActor: number;
  movies: Array<Actor> = new Array();

  constructor(private _actorService: SearchByActorService,
              private _movieService: AddMovieService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this._movieService.getActors().subscribe(res => {
      this.actors = res;
    })
  }

  findMovies(){
    this._actorService.findMoviesByActorId(this.selectedActor).subscribe(res => {
      this.movies = res;
    }, error =>{
        this.toastr.error("Something went wrong while trying to get movies.", "Error!");
    })
  }
}
