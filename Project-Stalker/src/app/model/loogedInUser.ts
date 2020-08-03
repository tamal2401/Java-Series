export class LoggedInUser {
    private user: string;
    private email: string;
    private token : string;
    private tokenExpirationDate: Date

    constructor(user, email, token, tokenExpiredIn) {
        this.user = user;
        this.email = email;
        this.token = token;
        this.tokenExpirationDate = tokenExpiredIn;
    }
}