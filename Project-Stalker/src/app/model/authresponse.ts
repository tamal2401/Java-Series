export class AuthResponse {
  public user: string;
  public email: string;
  public token: string;

  constructor(user, email, token) {
    this.user = user;
    this.email = email;
    this.token = token;
  }
}
