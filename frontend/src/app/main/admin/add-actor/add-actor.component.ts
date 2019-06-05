import { Component, OnInit } from '@angular/core';
import { AddActorService } from './add-actor.service';
import { Actor } from '../../../models/actor.model';
import { AbstractControlDirective } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-actor',
  templateUrl: './add-actor.component.html',
  styleUrls: ['./add-actor.component.css']
})
export class AddActorComponent implements OnInit {

  newActor: Actor = new Actor();
  actors: Array<Actor> = new Array();

  isNameValid: boolean = true;
  isLastNameValid: boolean = true;

  constructor(private _actorService: AddActorService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this.newActor.id = 0;
    this._actorService.getActors().subscribe(res => 
      {
        this.actors = res;
      }, error =>{
        this.toastr.error("Something went wrong while trying to get actors.", "Error!");
      });
  }

  addActor(){
    if (this.newActor.name == "" || this.newActor.name == null || this.newActor.name == undefined){
      this.isNameValid = false;
    }
    if (this.newActor.lastName == "" || this.newActor.lastName == null || this.newActor.lastName == undefined){
      this.isLastNameValid = false;
    }

    if (this.isNameValid && this.isLastNameValid){
      this._actorService.addActor(this.newActor).subscribe(res => 
        {
          var actorDB = new Actor();
          actorDB.id = res.id;
          actorDB.name = this.newActor.name;
          actorDB.lastName = this.newActor.lastName
          this.actors.push(actorDB);
          this.toastr.success("Actor successfully added.", "New actor!");
          this.newActor.name = '';
          this.newActor.lastName = '';
        }, error => {
          this.toastr.error("Something went wrong while trying to add new actor.", "Error!");
        });
    }
  }

}
