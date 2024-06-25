import { Post } from './post';
import { User } from './user';
export class Comment {

    id: number;
    user: User | null;
    post: Post | null;
    // inReplyToId: number;
    message: string;
    createdAt: string;
    updatedAt: string;

    constructor(
        id = 0,
        post = null,
        user = null,
        // inReplyToId = 0,
        message = '',
        createdAt = '',
        updatedAt = ''

    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.message = message;
        this.user = user;
        this.post = post;
        // this.inReplyToId = inReplyToId;

    }






}
