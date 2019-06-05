import { Component, OnInit } from '@angular/core';
import { Movie } from '../../../models/movie.model';
import { AddMovieService } from './add-movie.service';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginService } from '../../../login/login.service';
import { ToastrService } from 'ngx-toastr';
import { Genre } from 'src/app/models/genre.model';
import { MovieBasic } from 'src/app/models/movieBasic.model';
import { Actor } from 'src/app/models/actor.model';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {

  movie: Movie = new Movie();
  isSubmitTriggered: boolean = false;

  //genres
  genres: Array<Genre> = new Array();
  dropdownListGenres = [];
  selectedItemsGenres = [];
  dropdownSettingsGenres = {};

  //actors
  actors: Array<Actor> = new Array();
  dropdownListActors = [];
  selectedItemsActors = [];
  dropdownSettingsActors = {};


  constructor(private _movieService: AddMovieService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this.getGenres();
    this.getActors();

    //dropdown settings
    this.dropdownSettingsGenres = { 
      singleSelection: false, 
      text:"Select genres",
      selectAllText:'Select All',
      unSelectAllText:'UnSelect All',
      enableSearchFilter: true
    }; 

    this.dropdownSettingsActors = { 
      singleSelection: false, 
      text:"Select actors",
      selectAllText:'Select All',
      unSelectAllText:'UnSelect All',
      enableSearchFilter: true
    }; 
  }

  addMovie(form: any){
    this.isSubmitTriggered = true;
    if (form.form.valid && this.isRegexForTrailerValid()){
      this._movieService.addMovie(this.movie).subscribe(res => {
        //res.id
        this._movieService.connectGenresToMovie(res.id, res.movieName, this.selectedItemsGenres).subscribe(res1 => {
          this.movie = new Movie();
          this.isSubmitTriggered = false;
          this.selectedItemsGenres = [];
          this._movieService.connectActorsToMovie(res.id, res.movieName, this.selectedItemsActors).subscribe(res2 => {
            this.selectedItemsActors = [];
            this.toastr.success("Movie successfully added.", "New movie!");
          }, error => {
            this.toastr.error("Something went wrong while trying to add actors to a movie.", "Error!");
          }
          )
        },
        error => {
          this.toastr.error("Something went wrong while trying to add genres to a movie.", "Error!");
        });

        
      }, error => {
        this.toastr.error("Something went wrong while trying to add movie.", "Error!");
      });
      
    }

  }

  isRegexForTrailerValid(){
    var regex = new RegExp('^(https?://)?(www.youtube.com|youtu.?be)/.+$');
    if (regex.test(this.movie.trailer)){
      return true;
    }
    else{
      return false;
    }
  }

  getGenres(){
    this._movieService.getGenres().subscribe(res => 
      {
        this.genres = res;
        var g = new Array();
        this.genres.forEach(x => {
          g.push({'id': x.id, 'itemName': x.zanr});
        })
        this.dropdownListGenres = g;
      }, error =>{
        this.toastr.error("Something went wrong while trying to get genres.", "Error!");
      });
  }

  getActors(){
    this._movieService.getActors().subscribe(res =>{
      this.actors = res;
      var a = new Array();
      this.actors.forEach(x => {
        a.push({'id': x.id, 'itemName': x.name + ' ' + x.lastName});
      })
      this.dropdownListActors = a;
    }, error => {
      this.toastr.error("Something went wrong while trying to get genres.", "Error!");
    })
  }

}
