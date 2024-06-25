import { Skill } from './../../models/skill';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProfileComponent } from '../profile/profile.component';
import { Observable } from 'rxjs';
import { SkillService } from '../../services/skill.service';
import { SkillComponent } from '../skill/skill.component';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, ProfileComponent, SkillComponent],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent implements OnInit{
  users: User[] = [];
  newUser: User = new User();
  selected: User | null = null;
  editUser: User | null = null;
  loggedInUser: User | null = null;
  skills: Skill[] | null = null;

  uploadImg : User | null = null;
  //---------------------------------------------------------------------

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private userService : UserService, private authService: AuthService, private skillService: SkillService) {

  }

  //---------------------------------------------------------------------

  ngOnInit(): void {
    this.getLoggedInUser();
    
  }

  //---------------------------------------------------------------------


  displayUser(userPage: User) {
    this.selected = userPage;
    let url: string = '[profile]/' + userPage.id
    this.router.navigateByUrl(url);
  }

  getSkills(): void {
    this.skillService.index()
      .subscribe(skills => this.skills = skills);
  }

  reload() {
   this.getLoggedInUser();
      }
    
  
  getLoggedInUser(): void{
   this.authService.getLoggedInUser().subscribe({
    next: (user : User) => {
      this.editUser = user;
      console.log(user);

    },
    error: (err) => {
      console.log("something went wrong finding user")}
   });
  }


  
  updateUserProfile(user : User){
    this.userService.update(user, user.id).subscribe({
      next: (user) => {
        this.reload();
        this.selected = null;
        this.editUser = null;
        this.router.navigateByUrl('profile');
        
      },
      error: (err) => {
        console.log("something went wrong updating user")}
    });
  }

  setUpdatedUserProfile() {
    this.editUser = Object.assign({}, this.selected);
  }

  deleteUserProfile(id: number) {
    this.userService.destroy(id).subscribe({
      next: () => {
      this.reload();
      },
      error: () => {}
    });
  }
}
