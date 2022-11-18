import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subreddit } from 'src/app/models/subreddit';
import { SubredditService } from 'src/app/services/subreddit.service';

@Component({
  selector: 'app-sub-reddit',
  templateUrl: './sub-reddit.component.html',
  styleUrls: ['./sub-reddit.component.css']
})
export class SubRedditComponent implements OnInit {
  createSubredditForm: FormGroup;
  subreddit: Subreddit;

  constructor(private router: Router, private subredditService: SubredditService) {

    this.createSubredditForm = new FormGroup({
      title: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    });

    this.subreddit = {
      name: '',
      description: '',
      id: 0,
      numberofPosts: 0
    }
   }

  ngOnInit(): void {
  }

  createSubreddit(){
    this.subreddit.name = this.createSubredditForm.get('title')?.value;
    this.subreddit.description = this.createSubredditForm.get('description')?.value;

    this.subredditService.createSubreddit(this.subreddit)
      .subscribe(data => {
        this.router.navigateByUrl('/list-subreddits')}
        , error => {
          console.log("Error creating subreddits");
        });

  }

  discard(){
    this.router.navigateByUrl('/');
  }


}
