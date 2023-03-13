import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TrendingItems } from '../models/trending-items';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrendingItemsService {

constructor(private http: HttpClient) { }

  getTrendingItems(): Observable<Array<TrendingItems>>{
    
    return this.http.get<Array<TrendingItems>>("http://localhost:8080/api/trending-items");
  }

}
