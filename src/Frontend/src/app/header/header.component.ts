import { Component, OnInit, ViewChild } from '@angular/core';
import { MatLegacyMenu as MatMenu} from '@angular/material/legacy-menu';
import { Router } from '@angular/router';
import { CloseScrollStrategy, Overlay, OverlayRef } from "@angular/cdk/overlay";
import { ComponentPortal } from "@angular/cdk/portal";
import { faUser } from '@fortawesome/free-regular-svg-icons';
import { AuthService } from '../services/auth.service';
import { LoginOverlayComponent } from '../auth/login-overlay/login-overlay.component';
import { SignupOverlayComponent } from '../auth/signup-overlay/signup-overlay.component';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  faUser = faUser;
  isLoggedIn!: boolean;
  username!: string;
  menu!: MatMenu;
  overlayRef!: OverlayRef;
  
  constructor(private authService: AuthService, private router: Router, private overlay: Overlay) { }

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username);
  }

  login(): void{
    //create an  overlay
    this.overlayRef = this.overlay.create({
      positionStrategy: this.overlay.position().global().centerHorizontally().centerVertically(),
      hasBackdrop: true,
      scrollStrategy: this.overlay.scrollStrategies.close()
    });
    

    this.overlayRef.addPanelClass("justify-content-center");

    //Render the component
    this.overlayRef.attach(new ComponentPortal(LoginOverlayComponent));

  }

  signup(): void{

    this.overlayRef = this.overlay.create({
      positionStrategy: this.overlay.position().global().centerHorizontally().centerVertically(),
      hasBackdrop: true
    });
    

    this.overlayRef.addPanelClass("login-section");

    //Render the component
    this.overlayRef.attach(new ComponentPortal(SignupOverlayComponent));

  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('');
  }
}
