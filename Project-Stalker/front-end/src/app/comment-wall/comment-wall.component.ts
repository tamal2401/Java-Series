import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data-service';
import { ActivatedRoute, Params } from '@angular/router';
import { AuthService } from '../services/auth-service';
import { WallComment } from '../model/wallcomment';

@Component({
  selector: 'app-comment-wall',
  templateUrl: './comment-wall.component.html',
  styleUrls: ['./comment-wall.component.scss'],
})
export class CommentWallComponent implements OnInit {
  postee: string;
  comment : string;
  isLoggedIn : boolean = false;

  constructor(
    private dataService: DataService,
    private auth : AuthService,
    private aRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.aRoute.params.subscribe((params: Params) => {
      this.postee = params['user'];
    });
    this.auth.user.subscribe(user =>{
      if(!!user){
        this.isLoggedIn = true;
      }
    });
  }

  postComment(){
    let commentObj = new WallComment(this.postee,new Date(), this.comment);
    console.log(commentObj);
    this.dataService.postComment(commentObj);

  }

}
