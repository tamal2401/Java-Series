import { Component, OnInit, OnDestroy } from '@angular/core';
import { Post } from '../model/post';
import { DataService } from '../services/data-service';
import { Observable, Unsubscribable, Subscription } from 'rxjs';
import { AuthService } from '../services/auth-service';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { LoggedInUser } from '../model/loogedInUser';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit, OnDestroy {

  isEmpty: boolean = true;
  postArr: Array<Post> = [];
  subscriber: Subscription;
  loggedIn: boolean = false;
  loggedInUser : LoggedInUser = null;

  constructor(
    private dataService: DataService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.authService.user.subscribe((user) => {
      this.loggedInUser = user;
      this.loggedIn = !!user;
    });
    if (!this.loggedIn) {
      this.router.navigate(['login']);
    } else {
      let posts = new Observable<Post[]>();
      posts = this.dataService.getPosts(this.loggedInUser.getUserName);
      this.subscriber = posts.subscribe((res) => {
        if (res != null) {
          this.postArr = res;
          this.isEmpty = false;
        }
      });
    }

    // this.postArr = this.dataService.getPosts();
    // if(this.postArr!=null){
    //   this.isEmpty=false;
    // }
  }

  ngOnDestroy() {
    // this.subscriber.unsubscribe();
  }
}