import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { faComments } from '@fortawesome/free-solid-svg-icons';
import { Post } from 'src/app/models/post';


@Component({
  selector: 'app-post-tile',
  templateUrl: './post-tile.component.html',
  styleUrls: ['./post-tile.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class PostTileComponent implements OnInit {
  
  faComments = faComments;
  
  @Input()
  posts!: Array<Post>;

  constructor(private router: Router) {
    
   }

  ngOnInit(): void {
  }
  upvotePost(){}

  downvotePost(){}

  goToPost(id: number){
    this.router.navigateByUrl('view-post' + id);
  }

}
