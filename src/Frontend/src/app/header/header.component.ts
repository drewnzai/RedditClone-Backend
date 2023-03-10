import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatMenu } from '@angular/material/menu';
import { Router } from '@angular/router';
import { LoginRequestPayload } from '../models/login-request.payload'
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  loginRequestPayload!: LoginRequestPayload;
  loginForm!: FormGroup;
  isLoggedIn!: boolean;
  username!: string;
  menu!: MatMenu;
 
  
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();

    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
        
    });
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username);
  }

  login(): void{
    this.loginRequestPayload.username = this.loginForm.get('username')?.value;
    this.loginRequestPayload.password = this.loginForm.get('password')?.value;

    if(this.authService.login(this.loginRequestPayload)){
      this.router.navigateByUrl('home');
    }
  }

  signup(): void{

   
    

  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('');
  }
}
