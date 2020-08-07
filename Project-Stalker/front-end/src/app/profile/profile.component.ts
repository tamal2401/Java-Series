import { Component, OnInit, OnDestroy } from '@angular/core';
import { Post } from '../model/post';
import { DataService } from '../services/data-service';
import { Observable, Unsubscribable, Subscription } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {

  isEmpty: boolean = true;
  postArr: Array<Post> = [];
  subscriber: Subscription;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    let posts = new Observable<Post[]>();
    posts = this.dataService.getPosts();
    this.subscriber = posts.subscribe(res => {
      if (res != null) {
        this.postArr = res;
        this.isEmpty = false;
      }
    });
    // this.postArr = this.dataService.getPosts();
    // if(this.postArr!=null){
    //   this.isEmpty=false;
    // }
    console.log(this.isEmpty)
  }

  ngOnDestroy() {
    this.subscriber.unsubscribe();
  }
}
