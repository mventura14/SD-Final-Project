import { Skill } from './../../models/skill';
import { Component, numberAttribute } from '@angular/core';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { SkillService } from '../../services/skill.service';
import { SkillComponent } from '../skill/skill.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  imports: [CommonModule, FormsModule, EditProfileComponent, SkillComponent],
})
export class ProfileComponent {
  loggedInUser: User | null = null;
  users: User[] = [];
  newUser: User = new User();
  selected: User | null = null;
  editUser: User | null = null;
  reload: any;
  userSkill: Skill | null = null;
  showEmail: boolean = false;
  uploadImg: User | null = null;

  selectedSkill: string = '';
  slectedSkillId: number = 0;
  skillList: Skill[] = [];
  level:string = "";
  addingSkill: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
    private skillService: SkillService
  ) {}

  ngOnInit(): void {
    this.getuserProfile();
    console.log("It works!");
  }

  getuserProfile(): void {
    this.authService.getLoggedInUser().subscribe({
      next: (loggedInUser) => {
        this.loggedInUser = loggedInUser;
      },
      error: (error) => {
        console.log(
          'Error requesting user profile data. ProfileComponent.getUserProfile()'
        );
        this.router.navigateByUrl('profile');
      },
    });
  }

  getEditProfile(loggedInUser: User) {
    this.authService.getLoggedInUser().subscribe({
      next: (loggedInUser) => {
        this.loggedInUser = loggedInUser;
        this.router.navigateByUrl('editProfile');
      },
      error: (error) => {
        console.log(
          'Error requesting user profile data. ProfileComponent.getUserProfile()'
        );
        this.router.navigateByUrl('profile');
      },
    });
  }

  updateUserProfile(user: User) {
    this.userService.update(user, user.id).subscribe({
      next: (user) => {
        this.reload();
        this.selected = null;
        this.editUser = null;
        this.router.navigateByUrl('profile');
      },
      error: (err) => {
        console.log('something went wrong updating user');
      },
    });
  }

  setUpdatedUserProfile() {
    this.editUser = Object.assign({}, this.selected);
  }

  onOptionSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.selectedSkill = inputElement.value;
  }

  populateSkills(){
    if(!this.addingSkill){
      this.skillService.index().subscribe({
      next: (skills: Skill[])=>{console.log(skills)
        this.skillList = skills;
      },
      error:(err)=>{console.log("Caanot Populate skills profile.component populateSkills()" + err)}
    })
    }
    this.addingSkill = !this.addingSkill;
  }

  addSkill(skillName:String, level:String){
    this.skillList.forEach((skill)=>{
      if(skill.name === skillName){
        console.log(skill.id + " : " + level)
    
        this.userService.addSkill(skill.id,level).subscribe({
          next: (user:User)=>{
            this.loggedInUser = user;


          },
          error: ()=>{}
        })
      }
    })
  }
}
