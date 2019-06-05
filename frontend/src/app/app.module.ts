import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { AddActorComponent } from './main/admin/add-actor/add-actor.component';
import { AddGenreComponent } from './main/admin/add-genre/add-genre.component';
import { HttpClientModule } from '@angular/common/http';
import { AddMovieComponent } from './main/admin/add-movie/add-movie.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MyListsComponent } from './main/user/my-lists/my-lists.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { CookieService } from 'ngx-cookie-service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';
import { SearchByGenreComponent } from './main/user/search-by-genre/search-by-genre.component';
import { SearchByActorComponent } from './main/user/search-by-actor/search-by-actor.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent,
    AddActorComponent,
    AddGenreComponent,
    AddMovieComponent,
    MyListsComponent,
    SearchByGenreComponent,
    SearchByActorComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AngularMultiSelectModule
  ],
  providers: [CookieService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
