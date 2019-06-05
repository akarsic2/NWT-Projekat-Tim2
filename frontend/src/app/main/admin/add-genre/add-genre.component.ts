import { Component, OnInit } from '@angular/core';
import { Genre } from 'src/app/models/genre.model';
import { AddGenreService } from './add-genre.service';
import { Toast, ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-genre',
  templateUrl: './add-genre.component.html',
  styleUrls: ['./add-genre.component.css']
})
export class AddGenreComponent implements OnInit {

  newGenre: string;
  genres: Array<Genre> = new Array();
  isNameValid: boolean = true;

  constructor(private _genreService: AddGenreService,
    private toastr: ToastrService) { }

  ngOnInit() {
    this._genreService.getGenres().subscribe(res => 
      {
        this.genres = res;
      }, error =>{
        this.toastr.error("Something went wrong while trying to get genres", "Error!");
      });
  }

  addGenre(){
    if (this.newGenre == '' || this.newGenre == null || this.newGenre == undefined){
      this.isNameValid = false;
      return;
    }

    this._genreService.addGenre(this.newGenre).subscribe(res => 
      {
        this.genres.push(new Genre(res.id, this.newGenre));
        this.toastr.success("Genre successfully added.", "New genre!");
        this.newGenre = '';
      }, error =>{
        this.toastr.error("Something went wrong while trying to add new genre.", "Error!");
      });
  }

}
