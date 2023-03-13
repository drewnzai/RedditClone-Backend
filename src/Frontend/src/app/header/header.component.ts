import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatMenu } from '@angular/material/menu';
import { Router } from '@angular/router';
import { LoginRequestPayload } from '../models/login-request.payload'
import { SignupRequestPayload } from '../models/signup-request.payload';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  loginRequestPayload!: LoginRequestPayload;
  signupRequestPayload!: SignupRequestPayload
  loginForm!: FormGroup;
  signupForm!: FormGroup;
  isLoggedIn!: boolean;
  username!: string;
  menu!: MatMenu;
 
  
  constructor(private authService: AuthService, private router: Router) { 
    
    this.loginRequestPayload = {
      username: '',
      password: ''
    };

    this.signupRequestPayload = {
      username: '',
      password: '',
      email: ''
    };

  }

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();

    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
        
    });

    this.signupForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username);
  }

  login(): void{
   
      this.loginRequestPayload.username = this.loginForm.get('username')!.value;
      this.loginRequestPayload.password = this.loginForm.get('password')!.value;
  
      this.authService.login(this.loginRequestPayload).subscribe(data => {
        
    // $('#modal').hide();

          console.log("Success");
        this.router.navigateByUrl('home');


        
      });
    }
  

  signup(): void{
    
    this.signupRequestPayload.username = this.signupForm.get('username')!.value;
    this.signupRequestPayload.password = this.signupForm.get('password')!.value;
    this.signupRequestPayload.email = this.signupForm.get('email')!.value;

    this.authService.signup(this.signupRequestPayload).subscribe(data => {

      console.log("Successful Signup");
      this.router.navigateByUrl('home');

    });
    
  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('');
  }
}
