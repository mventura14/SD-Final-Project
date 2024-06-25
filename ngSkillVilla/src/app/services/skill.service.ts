import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Skill } from '../models/skill';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  skills: Skill[] = []

  private baseUrl = environment.baseUrl;
  //private baseUrl = 'http://localhost:8085/'; // adjust port to match server
  private url = this.baseUrl + 'api/skills';
  
  constructor(private http: HttpClient, private authService: AuthService) { }

  index(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('skillService.index(): error retrieving Skill: ' + err)
        );
      })
    );
  }

  create(skills: Skill): Observable<Skill> {
    return this.http.post<Skill>(this.url, skills, this.getHttpOptions()).pipe(
      catchError((err: any) =>{
        console.log(err);
        return throwError(
          () => new Error('skillService.create(): error creating skill' + err)
        );
      })
    );
  }

  update(skills: Skill, id: number): Observable<Skill>{
    return this.http.put<Skill>(this.url + "/" + id, skills, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('skillService.update(): error updating skill: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((error: any) => {
        console.log(error);
        return throwError(
          () => new Error('skillService.delete(): error deleting skill' + error)
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

  





}
