import { Post } from './../../models/post';
import { Comment } from './../../models/comment';
import { AuthService } from './../../services/auth.service';
import { PostService } from './../../services/post.service';
import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CommentService } from './../../services/comment.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-comments',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  @Input() parentPost: Post | null = null;
  comments: Comment[] = [];
  newComment: Comment | null = null;
  userId: number = 0;

  editComment: Comment | null = null;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private commentService: CommentService, private PostService: PostService, private authService: AuthService) { }

  ngOnInit(): void { 
    this.authService.getLoggedInUser().subscribe({
      next:(user:User)=>{this.userId = user.id}
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['parentPost'] && this.parentPost) {
      this.getComments(this.parentPost);
    }
  }

  //---------------------------------------------------------------------------

  setEditComment(comment: Comment): void {
    this.editComment = Object.assign({}, comment);
  }


  getComments(post: Post): void {
    if (post != null) {
      this.commentService.index(post).subscribe({
        next: (comments: Comment[]) => {
          this.comments = comments;
        },
        error: (error: any) => {
          console.log("Error loading comments", error);
        }
      });
    }
  }

  createNewComment(): void {
    this.newComment = new Comment();
  }

  // addComment(post: Post, comment: Comment): void {
  //   console.log(makes it here")
  //   this.commentService.addComment(post, comment, 
  //     (newComment) => {
  //       console.log("Comment added successfully", newComment);
  //       this.getComments(post); // Reload comments
  //       this.newComment = null;
  //     },
  //     (error) => {
  //       console.error("Error adding comment", error);
  //     }
  //   );
  // }

  deleteComment(comment: Comment): void {
    if (comment.post) {
      this.commentService.destroyComment(comment.post, comment).subscribe({
        next:()=>{
          if(this.parentPost){
            this.getComments(this.parentPost)
          }
        },
        error:(err)=>{console.log("Commnets.component deleteComment()" + err)}
      })
    }
  }

  updateComment = (comment: Comment): void => {
    if (comment.post) {
      this.commentService.updateComment( comment.post, comment).subscribe({
        next: () => {
          if(comment.post){
            this.getComments(comment.post)
          this.editComment = null;
          }
          
          
        },
        error: (err: any) => {
          console.log("Error updating comment", err);
        }
      });
    }
  }
}
