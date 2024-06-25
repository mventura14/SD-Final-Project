import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{

  newUser = new User();

  //--------------------------------------------
  constructor(private authService: AuthService, private router: Router) { 
  }
  
  ngOnInit(): void {
  }
;

  //--------------------------------------------

  register(user: User): void {
    console.log('Registering user:');
    console.log(user);
    this.authService.register(user).subscribe({
      next: (registeredUser) => {
        this.authService.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/profile');
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
          }
        });
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
    });
  }
  
  login(userLoggingin:User){
    this.authService.login(this.newUser.username, this.newUser.password).subscribe({
      next:(user)=>{
        this.router.navigateByUrl('/home')
      },
      error:(error)=>{
        console.log("login Failed -- registerComp login()")
      }
    })
  }
  //--------------------------------------------

  
  



}
