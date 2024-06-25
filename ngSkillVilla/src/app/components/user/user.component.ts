import { Component } from '@angular/core';
import { User } from '../../models/user';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {

  users: User[] = [];
  newUser: User = new User();
  selected: User | null = null;
  editUser: User | null = null;
  //---------------------------------------------------------------------

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private userService : UserService) {

  }

  //---------------------------------------------------------------------

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(
      {
        next: (params) => {
          console.log(params.get("userId"))
          let userIdStr = params.get("userId");
          if (userIdStr) {
            let userId = parseInt(userIdStr);
            if (isNaN(userId)) {
              this.router.navigateByUrl("invalid");
            } else (
              console.log(this.selected)
              
              
            )
          }
        }
      });
  }

  //---------------------------------------------------------------------


  displayUser(userPage: User) {
    this.selected = userPage;
    let url: string = '[profile]/' + userPage.id
    this.router.navigateByUrl(url);
  }

  reload() {
    this.userService.index().subscribe({
      next: (dbSkillVilla: User[]) => {
        console.log(dbSkillVilla)
        this.users = dbSkillVilla
      },
      error: (err) => {
        console.log("something went wrong with reload()")
      }
    })
  }

  addUser(user : User){
    this.userService.create(user).subscribe({
      next: (user) => {
        this.reload();
        this.newUser = new User();
      },
      error: (err) => {
        console.log("something went wrong adding user")}
    })
  }
  
  updateUser(user : User){
    this.userService.update(user, user.id).subscribe({
      next: (user) => {
        this.reload();
        this.selected = null;
        this.editUser = null;
      },
      error: (err) => {
        console.log("something went wrong updating user")}
    });
  }

  setUpdatedUser() {
    this.editUser = Object.assign({}, this.selected);
  }

  deleteUser(id: number) {
    this.userService.destroy(id).subscribe({
      next: () => {
      this.reload();
      },
      error: () => {}
    });
  }


}
