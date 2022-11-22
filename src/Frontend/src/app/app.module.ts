import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SignupComponent } from './auth/signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './auth/login/login.component';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { TokenInterceptor } from './token-interceptor';
import { PostTileComponent } from './shared/post-tile/post-tile.component';
import { SideBarComponent } from './shared/side-bar/side-bar.component';
import { SubredditSideBarComponent } from './shared/subreddit-side-bar/subreddit-side-bar.component';
import { VoteButtonComponent } from './shared/vote-button/vote-button.component';
import { SubRedditComponent } from './subreddit/sub-reddit/sub-reddit.component';
import { PostComponent } from './post/post/post.component';
import { ListSubredditsComponent } from './subreddit/list-subreddits/list-subreddits.component';
import { ListPostsComponent } from './post/list-posts/list-posts.component';
import { EditorModule } from '@tinymce/tinymce-angular';
import { CreatePostComponent } from './post/create-post/create-post.component';
import { ViewPostComponent } from './post/view-post/view-post.component';
import { CommentComponent } from './comment/comment.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserProfileComponent } from './auth/user-profile/user-profile.component';
import { MaterialModule } from 'src/material/material.module';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    PostTileComponent,
    SideBarComponent,
    SubredditSideBarComponent,
    VoteButtonComponent,
    SubRedditComponent,
    PostComponent,
    ListSubredditsComponent,
    ListPostsComponent,
    CreatePostComponent,
    ViewPostComponent,
    CommentComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgxWebstorageModule.forRoot(),
    ToastrModule.forRoot(),
    HttpClientModule,
    EditorModule,
    MaterialModule,
    NgbModule
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
