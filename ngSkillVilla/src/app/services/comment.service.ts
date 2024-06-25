import { PostService } from './post.service';
import { Post } from './../models/post';
import { Comment } from './../models/comment';
import { HttpClient } from '@angular/common/http';
import { Injectable, SimpleChanges } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, connect, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private baseUrl = environment.baseUrl;
  //private baseUrl = 'http://localhost:8085/'; // adjust port to match server
  private url = this.baseUrl + 'api/posts/';

  constructor(private http: HttpClient, private authService: AuthService, private PostService: PostService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(post: Post): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url + post.id + "/comments", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('commentService.index(): error retrieving comments: ' + err)
        );
      })
    );
  }

  createComment(post: Post, comment: Comment): Observable<Comment> {
    let communityId;
    if (post.community != null) {
      communityId = post.community.id;
    } else {
      communityId = 0;
    }
    return this.http.post<Comment>(this.baseUrl + "api/communities/" + communityId + "/posts/" + post.id, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('commentService.createComment(): error creating comment: ' + err)
        );
      })
    );
  }

  updateComment(post: Post, comment: Comment): Observable<Comment> {
    let communityId;
    if (post.community != null) {
      communityId = post.community.id;
    } else {
      communityId = 0;
    }
    return this.http.put<Comment>(this.url + post.id + "/comments/" + comment.id, comment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('commentService.updateComment(): error updating comment: ' + err)
        );
      })
    );
  }

  destroyComment(post: Post, comment: Comment): Observable<void> {
    return this.http.delete<void>(this.url + post.id + "/comments/" + comment.id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('commentService.destroyComment(): error deleting comment: ' + err)
        );
      })
    );
  }

  addComment(post: Post, comment: Comment, onSuccess: (comment: Comment) => void, onError: (error: any) => void): void {
    this.createComment(post, comment).subscribe({
      next: (newComment) => {
        console.log('Comment added successfully', newComment);
        onSuccess(newComment);
      },
      error: (err) => {
        console.error('Error adding comment', err);
        onError(err);
      }
    });
  }

  loadComments(post: Post, onSuccess: (comments: Comment[]) => void, onError: (error: any) => void): void {
    this.index(post).subscribe({
      next: (comments) => {
        console.log('Comments loaded successfully', comments);
        onSuccess(comments);
      },
      error: (err) => {
        console.error('Error loading comments', err);
        onError(err);
      }
    });
  }
}
