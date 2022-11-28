import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { LoginRequestPayload } from '../../models/login-request.payload';
import { Router, ActivatedRoute } from '@angular/router'
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login-overlay',
  templateUrl: './login-overlay.component.html',
  styleUrls: ['./login-overlay.component.css']
})
export class LoginOverlayComponent implements OnInit {
  loginForm!: FormGroup;
  loginRequestPayload: LoginRequestPayload;
  registerMessage!: string;
  isError!: boolean;

  constructor(private authService: AuthService, 
    private router: Router, private activatedRoute: ActivatedRoute,
    private toast: ToastrService) { 

    this.loginRequestPayload = {
      username: "",
      password: ""
  }
}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      
  });
  
    this.activatedRoute.queryParams.subscribe(params => {
      if (params !== undefined && params['registered'] === 'true'){
        this.toast.success("Successful registration");
        this.registerMessage = "Please check your inbox for activation link and activate before login";
      }
    });
  }

  
  login(){
    this.loginRequestPayload.username = this.loginForm.get('username')?.value;
    this.loginRequestPayload.password = this.loginForm.get('password')?.value;

    this.authService.login(this.loginRequestPayload).subscribe(data => 
      {
        if (data)
        {
          this.isError = false;
          this.router.navigateByUrl('/home');
          this.toast.success("Login successful")

      }
      else{
        this.isError = true;
      }
    });

  }

}
