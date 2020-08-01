import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticationComponent } from './authentication/authentication.component';
import { ProfileComponent } from './profile/profile.component';
import { CommentWallComponent } from './comment-wall/comment-wall.component';


const routes: Routes = [
  {path:'', component: AuthenticationComponent},
  {path:'profile', component: ProfileComponent},
  {path:'wall', component: CommentWallComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
