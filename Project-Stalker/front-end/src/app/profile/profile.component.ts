import { Component, OnInit, OnDestroy } from '@angular/core';
import { Post } from '../model/post';
import { DataService } from '../services/data-service';
import { Observable, Unsubscribable, Subscription } from 'rxjs';
import { AuthService } from '../services/auth-service';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { LoggedInUser } from '../model/loogedInUser';
import { Clipboard } from '@angular/cdk/clipboard';

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
  profileUrl : string = 'http://surprise';

  constructor(
    private dataService: DataService,
    private authService: AuthService,
    private router: Router,
    private clipboard : Clipboard
  ) {}

  ngOnInit() {
    this.authService.user.subscribe((user) => {
      this.loggedInUser = user;
      this.loggedIn = !!user;
    });
    if (!this.loggedIn) {
      console.log(1);
      this.router.navigate(['login']);
    } else {
      console.log(2);
      let posts = new Observable<Post[]>();
      posts = this.dataService.getPosts(this.loggedInUser.getUserName);
      this.subscriber = posts.subscribe((res) => {
        if (res != null) {
          this.postArr = res;
          this.isEmpty = false;
        }
      });
    }

    // // This is to test the post array functionality
    // this.postArr = this.dataService.getPosts();
    // if(this.postArr!=null){
    //   this.isEmpty=false;
    // }

  }

  ngOnDestroy() {
    // this.subscriber.unsubscribe();
  }

  copy(){
    this.clipboard.copy(this.profileUrl);
  }
}
