import { Component, OnInit } from '@angular/core';
import { Post } from '../models/post';
import { PostService } from '../services/post.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home-tab',
  templateUrl: './home-tab.component.html',
  styleUrls: ['./home-tab.component.css']
})
export class HomeTabComponent implements OnInit {
  
  posts: Array<Post> = [];
  isLoggedIn!: boolean;


  constructor(private authService: AuthService, private postService: PostService) {
    this.postService.getAllPosts().subscribe(post => {
      this.posts = post;
    });
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();

  }


}
