import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { Subreddit } from 'src/app/models/subreddit';
import { PostService } from 'src/app/services/post.service';
import { SubredditService } from 'src/app/services/subreddit.service';
import { CreatePostPayload } from '../../models/create-post-payload';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
   subreddits!: Array<Subreddit>;
   createPostForm!: FormGroup;
   postPayload: CreatePostPayload;

  constructor(private router: Router, private subredditService: SubredditService,
    private postService: PostService) { 

      this.postPayload = {
        postName: '',
        url: '',
        description: '',
        subredditName: ''
      }

    }

  ngOnInit(): void {
    
    this.createPostForm = new FormGroup({
      postName: new FormControl('', Validators.required),
      subredditName: new FormControl('', Validators.required),
      url: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });
    
    this.subredditService.getAllSubreddits().subscribe(data => {
      this.subreddits = data;

  }, error => {
    throwError(error);
  }
  );

  }

  discard(){
    this.router.navigateByUrl('/');
  }

  createPost(){
    this.postPayload.postName = this.createPostForm.get('postName')?.value;
    this.postPayload.subredditName = this.createPostForm.get('subredditName')?.value;
    this.postPayload.url = this.createPostForm.get('url')?.value;
    this.postPayload.description = this.createPostForm.get('description')?.value;

    this.postService.createPost(this.postPayload).subscribe((data) => {
      this.router.navigateByUrl('/');
    }, error => {
      throwError(error);
    })
  }
  }

