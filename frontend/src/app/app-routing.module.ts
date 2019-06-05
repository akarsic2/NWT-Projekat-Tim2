import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { AddMovieComponent } from './main/admin/add-movie/add-movie.component';
import { AddActorComponent } from './main/admin/add-actor/add-actor.component';
import { AddGenreComponent } from './main/admin/add-genre/add-genre.component';
import { MyListsComponent } from './main/user/my-lists/my-lists.component';
import { SearchByGenreComponent } from './main/user/search-by-genre/search-by-genre.component';
import { SearchByActorComponent } from './main/user/search-by-actor/search-by-actor.component';


const appRoutes: Routes = [
  { path: '' , component: LoginComponent},
  {path:'register', component: RegisterComponent},
  {path:'main', component: MainComponent, children: [
    {path:'add-movie', component:AddMovieComponent},
    {path:'add-actor', component:AddActorComponent},
    {path:'add-genre', component:AddGenreComponent},
    {path:'my-lists', component:MyListsComponent},
    {path:'search-by-genre', component:SearchByGenreComponent},
    {path:'search-by-actor', component:SearchByActorComponent}
  ]},
  {path: '**', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }