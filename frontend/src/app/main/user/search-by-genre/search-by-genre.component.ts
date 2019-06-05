import { Component, OnInit } from '@angular/core';
import { AddGenreService } from '../../admin/add-genre/add-genre.service';
import { Genre } from 'src/app/models/genre.model';
import { MovieBasic } from 'src/app/models/movieBasic.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-search-by-genre',
  templateUrl: './search-by-genre.component.html',
  styleUrls: ['./search-by-genre.component.css']
})
export class SearchByGenreComponent implements OnInit {

  genres: Array<Genre> = new Array();
  selectedGenre: number;
  movies: Array<MovieBasic> = new Array();

  constructor(private _genreService: AddGenreService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this._genreService.getGenres().subscribe(res => {
      this.genres = res;
    })
  }

  findMovies(){
    this._genreService.findMoviesByGenreId(this.selectedGenre).subscribe(res => {
      this.movies = res;
    }, error =>{
        this.toastr.error("Something went wrong while trying to get movies.", "Error!");
    })
  }
}
