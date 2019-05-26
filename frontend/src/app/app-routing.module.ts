import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { AddMovieComponent } from './main/add-movie/add-movie.component';
import { AddActorComponent } from './main/add-actor/add-actor.component';
import { AddGenreComponent } from './main/add-genre/add-genre.component';

const appRoutes: Routes = [
  { path: '' , component: LoginComponent},
  {path:'register', component: RegisterComponent},
  {path:'main', component: MainComponent, children: [
    {path:'add-movie', component:AddMovieComponent},
    {path:'add-actor', component:AddActorComponent},
    {path:'add-genre', component:AddGenreComponent}
  ]},
  {path: '**', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }