import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { Router, RouterLink } from '@angular/router';
import { CommunityComponent } from '../community/community.component';
import { ProfileComponent } from '../profile/profile.component';
import { AuthService } from '../../services/auth.service';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';

@Component({
  selector: 'app-navigation-bar',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    LoginComponent,
    RegisterComponent,
    CommunityComponent,
    ProfileComponent,
    EditProfileComponent
  ],
  templateUrl: './navigation-bar.component.html',
  styleUrl: './navigation-bar.component.css'
})
export class NavigationBarComponent {

  
  constructor(private authService: AuthService, private router:Router){};


  checkLogin(){
    return this.authService.checkLogin();
  }



  logout(): void {
    this.authService.logout();
    this.router.navigateByUrl('login')
  }
}
