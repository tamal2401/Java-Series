export class LoggedInUser {
    private user: string;
    private email: string;
    private token : string;

    constructor(user, email, token) {
        this.user = user;
        this.email = email;
        this.token = token;
    }
}
