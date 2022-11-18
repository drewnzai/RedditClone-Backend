import { Component, OnInit } from '@angular/core';
import { Subreddit } from 'src/app/models/subreddit';
import { SubredditService } from 'src/app/services/subreddit.service';

@Component({
  selector: 'app-subreddit-side-bar',
  templateUrl: './subreddit-side-bar.component.html',
  styleUrls: ['./subreddit-side-bar.component.css']
})
export class SubredditSideBarComponent implements OnInit {

  subreddits!: Array<Subreddit>;
  displayAll!: boolean;
  
  constructor(private subredditService: SubredditService) {
    this.subredditService.getAllSubreddits().subscribe(data => {
      if (data.length > 4){
        this.subreddits = data.splice(0, 3);
        this.displayAll = true;
      } 
      else 
      this.subreddits = data;
    });

   }

  ngOnInit(): void {
  }

}
