import { Component, OnInit } from '@angular/core';
import { Genre } from 'src/app/models/genre.model';

@Component({
  selector: 'app-add-genre',
  templateUrl: './add-genre.component.html',
  styleUrls: ['./add-genre.component.css']
})
export class AddGenreComponent implements OnInit {

  newGenre: string;
  genres: Array<Genre> = new Array();

  constructor() { }

  ngOnInit() {
    //mocked data
    this.genres.push(new Genre(1, 'Action'));
    this.genres.push(new Genre(1, 'Adventure'));
    this.genres.push(new Genre(1, 'Comedy'));
    this.genres.push(new Genre(1, 'Crime'));
    this.genres.push(new Genre(1, 'Drama'));
    this.genres.push(new Genre(1, 'Fantasy'));
    this.genres.push(new Genre(1, 'Historical'));
    this.genres.push(new Genre(1, 'Mystery'));
    this.genres.push(new Genre(1, 'Science fiction'));
    this.genres.push(new Genre(1, 'Thriller'));
  }

  addGenre(){
    this.genres.push(new Genre(1, this.newGenre));
  }

}
