export class AuthResponse {
    private user: string;
    private email: string;
    constructor(user, email) {
        this.user = user;
        this.email = email;
    }
}