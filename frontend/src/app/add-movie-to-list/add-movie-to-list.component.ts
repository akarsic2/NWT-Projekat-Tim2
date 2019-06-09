import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-movie-to-list',
  templateUrl: './add-movie-to-list.component.html',
  styleUrls: ['./add-movie-to-list.component.css']
})

export class AddMovieToListComponent implements OnInit {

  constructor() { }

  ngOnInit() {
      // this.listId = this.route.snapshot.paramMap.get("id");
  }


}
