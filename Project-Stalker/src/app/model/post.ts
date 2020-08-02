export class Post {
    public postTime: string;
    public post: string;
    constructor(dateTime, post) {
        this.postTime = dateTime;
        this.post = post;
    }
}