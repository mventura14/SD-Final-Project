import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Community } from '../models/community';
import { Observable, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {


  communities: Community[] = [];

  private baseUrl = environment.baseUrl;
  // private baseUrl = 'http://localhost:8085/';
  private url = this.baseUrl + 'api/communities';

  //---------------------------------------------------------------------

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }

  //---------------------------------------------------------------------

  index(): Observable<Community[]> {
    return this.http.get<Community[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('commService.index(): error retrieving communities: ' + err)
        );
      })
    );
  }

  showCommunity(id:number):Observable<Community>{
    return this.http.get<Community>(this.url + "/" + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('todoService.index(): error retrieving pokemon: ' + err)
        );
      })
    );
  }

  create(community: Community): Observable<Community> {
    return this.http.post<Community>(this.url, community, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('commService.create(): error creating community: ' + err)
        );
      })
    );
  }

  update(communities: Community, id: number): Observable<Community> {
    return this.http.put<Community>(this.url + "/" + id, communities, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('communities.update(): error updating community: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((error: any) => {
        console.log(error);
        return throwError(
          () => new Error('commService.delete(): error deleting community' + error)
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


