import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticationComponent } from './authentication/authentication.component';
import { ProfileComponent } from './profile/profile.component';
import { CommentWallComponent } from './comment-wall/comment-wall.component';
import { AuthGuard } from './authentication/auth-guard';


const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path:'login', component: AuthenticationComponent},
  {path:'profile', canActivate: [AuthGuard],component: ProfileComponent},
  {path:'wall', component: CommentWallComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
