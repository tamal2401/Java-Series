export class AuthResponse {
    public user: string;
    public email: string;
    public token : string;
    public expiresIn : number;
    constructor(user, email, token, expiresIn) {
        this.user = user;
        this.email = email;
        this.token = token;
        this.expiresIn = expiresIn;
    }
}