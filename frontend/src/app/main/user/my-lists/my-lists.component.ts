import { Component, OnInit } from '@angular/core';
import { MyListsService } from './my-lists.service';
import { List } from 'src/app/models/list.model';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-my-lists',
  templateUrl: './my-lists.component.html',
  styleUrls: ['./my-lists.component.css']
})
export class MyListsComponent implements OnInit {

  newList: string;
  lists: Array<List> = new Array();
  isNameValid: boolean = true;

  constructor(private _listService: MyListsService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this._listService.getLists().subscribe((res:any) => 
      {
        if (res.length != 0 )
          this.lists = res[0].movieLists;
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

}
