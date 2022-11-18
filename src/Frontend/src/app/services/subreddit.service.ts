import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subreddit } from '../models/subreddit';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {
  constructor(private http: HttpClient) { }

  getAllSubreddits(): Observable<Array<Subreddit>> {
    return this.http.get<Array<Subreddit>>('http://localhost:8080/api/subreddit');
  }

  createSubreddit(subreddit: Subreddit): Observable<Subreddit>{
    
    return this.http.post<Subreddit>('http://localhost:8080/api/subreddit', subreddit);

  }
}