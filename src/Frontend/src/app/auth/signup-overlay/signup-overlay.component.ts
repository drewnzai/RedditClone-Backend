import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { signupRequestPayload } from '../../models/signup-request.payload';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-signup-overlay',
  templateUrl: './signup-overlay.component.html',
  styleUrls: ['./signup-overlay.component.css']
})
export class SignupOverlayComponent implements OnInit {
  signupRequestPayload: signupRequestPayload;
  signupForm!: FormGroup;

  constructor(private authService: AuthService, private toast: ToastrService, private router: Router) { 
    this.signupRequestPayload = {
      username: "",
      password: "",
      email: ""
    };
  }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email])
    });
  }
  signup(): void{
    this.signupRequestPayload.email = this.signupForm.get('email')?.value;
    this.signupRequestPayload.password = this.signupForm.get('password')?.value;
    this.signupRequestPayload.username = this.signupForm.get('username')?.value;

    this.authService.signup(this.signupRequestPayload).subscribe(() => {
      this.router.navigate(["/login"], {queryParams: { registered: 'true'}});
    },
     () => {
      this.toast.error('Registration unsuccessful, try again');
     });
  }
}
