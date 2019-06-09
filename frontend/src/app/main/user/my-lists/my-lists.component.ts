import { Component, OnInit, NgModule, Pipe } from '@angular/core';
import { MyListsService } from './my-lists.service';
import { List } from 'src/app/models/list.model';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Movie } from 'src/app/models/movie.model';

@Pipe({
  name: 'filter',
  pure: false
})

@Component({
  selector: 'app-my-lists',
  templateUrl: './my-lists.component.html',
  styleUrls: ['./my-lists.component.css']
})
export class MyListsComponent implements OnInit {

  newList: string;
  lists: Array<List> = new Array();
  isNameValid: boolean = true;
  movies: Array<Movie> = new Array();
  movieFilter: string;
  selectedListId;
  selectedMovieId;
  selectedListMovies: Array<Movie> = new Array();

  constructor(private _listService: MyListsService,
              private toastr: ToastrService,
              private _router:Router) { }

  ngOnInit() {
    this._listService.getLists().subscribe((res:any) => 
      {
        if (res.length != 0 )
          this.lists = res[0].movieLists;
      }, error => {
        this.toastr.error("Something went wrong while trying to get user's lists.", "Error!");
      });
      this._listService.getMovies().subscribe((res:any) => 
      {
        if (res.length != 0 )
          this.movies = res;
          console.log(this.movies);
      }, error => {
        this.toastr.error("Something went wrong while trying to get user's lists.", "Error!");
      });
  }

  addList(){
    if (this.newList == '' || this.newList == null || this.newList == undefined){
      this.isNameValid = false;
      return;
    }

    this._listService.addList(this.newList).subscribe(res => 
      {
        this.lists.push(new List(res.id, this.newList));
        this.newList = '';
        this.toastr.success("List successfully added.", "New list!");
      }, error => {
        this.toastr.error('Something went wrong while trying to add new list.', "Error!");
      });
  }

  openList(listId) {
    

    this.selectedListMovies = [];
    this.selectedListId = listId;

    this._listService.getMovies().subscribe((res:any) => 
      {
        if (res.length != 0 )
          this.movies = res;
      }, error => {
        this.toastr.error("Something went wrong while trying to get user's lists.", "Error!");
      });

    let moviesIds = [];

    fetch('http://localhost:8084/getmoviesforlist/' + listId)
      .then(response => {console.log(response); return response.json();})
      .then(text => {moviesIds = text; console.log(moviesIds);})
      .then(() => {
        this.movies.forEach(m => {
          if (moviesIds.indexOf(m.id) >= 0) {
            console.log('Ima film:');
            console.log(m);
            this.selectedListMovies.push(m);
          }
        })
      });
      // .then(() => console.log('Selected lista: '))
      // .then(() => console.log(this.selectedListMovies));
  }

  selectMovie(movieId) {
    this.selectedMovieId = movieId;
  }
  
  addSelectedMovieToList() {
    this._listService.addMovieToList(this.selectedMovieId, this.selectedListId);
  }
}
