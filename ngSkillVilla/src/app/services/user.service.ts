import { HttpClient } from '@angular/common/http';
import { User } from './../models/user';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  users: User[] = []
  
  private baseUrl = environment.baseUrl;
  // private baseUrl = 'http://localhost:8085/'; // adjust port to match server
  private url = this.baseUrl + 'api/users';



  constructor(private http: HttpClient, private authService: AuthService) { }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('userService.index(): error retrieving user: ' + err)
        );
      })
    );
  }



  create(users: User): Observable<User> {
    return this.http.post<User>(this.url, users, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('userService.create(): error retrieving user: ' + err)
        );
      })
    );
  }

  update(users: User, id:number): Observable<User> {
    return this.http.put<User>(this.url + "/" + id, users, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('users.create(): error retrieving user: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((error: any) => {
        console.log(error);
        return throwError(
          () => new Error('userService.delete(): error deleting user' + error)
        );
      })
    );
  }


  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  addSkill(skillId: number, level:String){
    return this.http.put<User>(this.url + "/id/skills/" + skillId,level, this.getHttpOptions()).pipe(
      catchError((error: any) => {
        console.log(error);
        return throwError(
          () => new Error('userService.delete(): error deleting user' + error)
        );
      })
    );
  }
}
