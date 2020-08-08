import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Post } from '../model/post';
import { WallComment } from '../model/wallcomment';

@Injectable({
    providedIn: "root"
})
export class DataService {

    arrofPosts :Post[] = [{postTime:'12:30', post:'first'},{postTime:'12:30', post:'second'},{postTime:'12:30', post:'third'}];

    constructor(private http: HttpClient, private router: Router) { }

    getPosts(name:string) {
        return this.http.get<Post[]>(`http://localhost:8080/posts/${name}`);
    }

    postComment(commentObj : WallComment){
      const userName = commentObj.getUserName;
      this.http.post<string>(`http://localhost:8080/wall/${userName}`,
      commentObj);
    }
}
