import { Comment } from './comment';
import { Community } from './community';
import { Location } from './location';
import { PostCategory} from './post-category';
import { User } from './user';

export class Post {
    id: number;
    user: User;
    community: Community | null;
    description: string;
    enabled: boolean;
    location: Location | null;
    postCategory: PostCategory;
    imageUrl: string;
    createdAt: string;
    updatedAt: string;
    comments: Comment[];
    newComment: Comment;
    
    constructor(
        id:number = 0,
        user:User = new User(),
        community: Community | null = null,
        description: string = '',
        enabled: boolean = false,
        location: Location | null = null,
        imageUrl: string = '',
        postCategory: PostCategory = new PostCategory(),
        createdAt: string = '',
        UpdatedAt: string = '',
        comments: Comment[] = [],
        newComment:Comment = new Comment()
    ) {
        this.id = id;
        this.user = user;
        this.community = community;
        this.description = description;
        this.enabled = enabled;
        this.location = location;
        this.postCategory = postCategory;
        this.createdAt = createdAt;
        this.updatedAt = UpdatedAt;
        this.imageUrl = imageUrl;
        this.comments = comments;
        this.newComment = newComment;

    }

}
