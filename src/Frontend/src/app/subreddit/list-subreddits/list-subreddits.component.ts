import { Component, OnInit } from '@angular/core';
import { throwError } from 'rxjs';
import { Subreddit } from 'src/app/models/subreddit';
import { SubredditService } from 'src/app/services/subreddit.service';

@Component({
  selector: 'app-list-subreddits',
  templateUrl: './list-subreddits.component.html',
  styleUrls: ['./list-subreddits.component.css']
})
export class ListSubredditsComponent implements OnInit {

  subreddits!: Array<Subreddit>;
  
  constructor(private subredditService: SubredditService) { }

  ngOnInit(): void {
  
    this.subredditService.getAllSubreddits().subscribe(data => {
      this.subreddits = data;

  }, error => {
    throwError(error);
  }
  );

  }

}
