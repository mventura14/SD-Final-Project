import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Category } from '../models/category';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  
  
  categories: Category[] = [];
  private baseUrl = environment.baseUrl;
  // private baseUrl = 'http://localhost:8085/'; 
  private url = this.baseUrl + 'api/categories';



  constructor(private http: HttpClient, private authService: AuthService) { }

  index(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('categoryService.index(): error retrieving categories: ' + err)
        );
      })
    );
  }



  create(categories: Category): Observable<Category> {

    return this.http.post<Category>(this.url, categories, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('categoryService.create(): error creating category: ' + err)
        );
      })
    );
  }

  update(categories: Category, id:number): Observable<Category> {
    return this.http.put<Category>(this.url + "/" + id, categories, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('categoryService.update(): error updating category: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((error: any) => {
        console.log(error);
        return throwError(
          () => new Error('categoryService.delete(): error deleting category' + error)
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